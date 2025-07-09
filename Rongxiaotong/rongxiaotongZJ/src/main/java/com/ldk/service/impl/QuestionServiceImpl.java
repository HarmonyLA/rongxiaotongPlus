package com.ldk.service.impl;
import java.util.concurrent.TimeUnit;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldk.api.dto.PageDTO;
import com.ldk.api.json.ResponseResult;
import com.ldk.mapper.QuestionMapper;
import com.ldk.pojo.Question;
import com.ldk.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseResult searchByTitle(String keyword) {
        // 1. 先尝试从Redis获取
        List<Question> result = (List<Question>) redisTemplate.opsForValue().get("questions:search:" + keyword);

        if (result == null || result.isEmpty()) {
            // 2. 数据库查询
            QueryWrapper<Question> wrapper = new QueryWrapper<>();
            wrapper.like("title", keyword)
                    .or().like("content", keyword)
                    .orderByDesc("create_time");
            result = questionMapper.selectList(wrapper);

            // 3. 存入Redis（5分钟过期）
            redisTemplate.opsForValue().set("questions:search:" + keyword, result, 5, TimeUnit.MINUTES);
        }

        return new ResponseResult(200, "SUCCESS", result, null);
    }

    @Override
    public ResponseResult getAllQuestions(Integer currentPage, Integer pageSize) {
        Page<Question> page = new Page<>(currentPage, pageSize);
        Page<Question> result = questionMapper.selectPage(page, null);

        PageDTO pageDTO = new PageDTO()
                .setCurrent(result.getCurrent())
                .setSize(result.getSize())
                .setTotal(result.getTotal())
                .setPages(result.getPages());

        return new ResponseResult(200, "SUCCESS", result.getRecords(), pageDTO);
    }
}
