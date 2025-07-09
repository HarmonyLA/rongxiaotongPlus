package com.ldk.service.impl;

import com.ldk.api.json.ResponseResult;
import com.ldk.mapper.ExpertMapper;
import com.ldk.pojo.Expert;
import com.ldk.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private ExpertMapper expertMapper;

    @Override
    public ResponseResult getAllExperts() {
        List<Expert> experts = expertMapper.selectList(null);
        return new ResponseResult(200, "SUCCESS", experts, null);
    }
}