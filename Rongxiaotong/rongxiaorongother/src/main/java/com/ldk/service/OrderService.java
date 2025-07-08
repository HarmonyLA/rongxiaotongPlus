package com.ldk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Order;

public interface OrderService extends IService<Order> {
    //查询近期热门商品信息
    ResponseResult getHotOrders();

    //   所有商品
    ResponseResult  getAllOrders(Integer currentPage,Integer pageSize);

    // 根据id 查询数据
    ResponseResult findOrderByOrderId(Integer id);

    // 模糊查询
    ResponseResult  selectByLikeName(String searchName);

    // 上传图片
    ResponseResult doUpLoad(String url,String username,Integer id);

    // 发布信息
    ResponseResult publishInfo(Order order);


}
