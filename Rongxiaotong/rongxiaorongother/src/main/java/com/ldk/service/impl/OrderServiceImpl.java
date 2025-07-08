package com.ldk.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldk.api.dto.PageDTO;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Order;
import com.ldk.api.util.JWTToken;
import com.ldk.mapper.OrderMapper;
import com.ldk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public ResponseResult getHotOrders() {
        //TODO
        // 查询这些数据 可以考虑使用Redis缓存  效率非常高  0 -1 查询所有数据
        List hotlist = redisTemplate.opsForList().range("hotOrders", 0, -1);
        if (hotlist == null || hotlist.isEmpty()) {
            System.out.println("查询mysql数据库");
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_status", 0).last("LIMIT " + 10);
            List<Order> list = this.list(wrapper);
            if (list == null || list.isEmpty()) {
                return new ResponseResult(201, "服务器正忙,加载失败!", null,null);
            }
            //存储到redis中
            redisTemplate.opsForList().leftPushAll("hotOrders", list);
            return new ResponseResult(200, "SUCCESS", list,null);
        }
        System.out.println("查询redis数据库");
        return new ResponseResult(200, "SUCCESS", hotlist,null);
    }

    @Override
    public ResponseResult getAllOrders(Integer currentPage,Integer pageSize) {
        //分页条件     Page<>(第几页，每页显示的条数)
        Page<Order> page = new Page<>(currentPage,pageSize);
        Page<Order> orderPage = orderMapper.selectPage(page, null);
        List<Order> list = orderPage.getRecords();// 分页之后的数据
        //总记录数
        long total = orderPage.getTotal();
        //总页数
        long pages = orderPage.getPages();
        // 当前页
        long current = orderPage.getCurrent();
        // 每页的条数
        long size = orderPage.getSize();

        PageDTO pageDTO = new PageDTO()
                .setPages(pages).setSize(size).setTotal(total).setCurrent(current);
        return new ResponseResult(200, "SUCCESS", list ,pageDTO);
    }

    @Override
    public ResponseResult findOrderByOrderId(Integer id) {
        if(id == null){
            return   new ResponseResult(201,"参数异常",null,null);
        }
        QueryWrapper<Order>  wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",id);
        Order order = this.getOne(wrapper);
        if(order == null){
            return   new ResponseResult(202,"数据异常",null,null);
        }

        return new ResponseResult(200,"SUCCESS",order,null) ;
    }

    @Override
    public ResponseResult selectByLikeName(String searchName) {
        if("".equals(searchName) || searchName ==null){
            return new ResponseResult(201,"请输入查询的信息",null,null);
        }
        // 模糊查询
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.like("title",searchName).or().like("content",searchName);
        List<Order> list = orderMapper.selectList(wrapper);
        if(list.isEmpty()){
            return new ResponseResult(202,"没有数据",null,null);
        }
           return new ResponseResult(200,"SUCCESS",list,null);
    }

    @Override
    public ResponseResult doUpLoad(String url,String username,Integer id) {
        if(url == null ){
             return   new ResponseResult(201,"上传失败",null,null);
        }
        return   new ResponseResult(200,"上传成功",url,null);

    }

    @Override
    public ResponseResult publishInfo(Order order) {
        // 补充参数
        order.setType("needs")
                .setOrderStatus(0)
                        .setCreateTime(new Date())
                                .setUpdateTime(new Date());
            // 添加操作
        int row = orderMapper.insert(order);

        if(row != 1 ){
            return   new ResponseResult(201,"发布失败",null,null);
        }
        return   new ResponseResult(200,"发布成功",null,null);
    }
}
