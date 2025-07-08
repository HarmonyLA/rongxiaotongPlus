package com.ldk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldk.api.pojo.Order;
import com.ldk.api.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
