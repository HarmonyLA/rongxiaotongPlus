package com.ldk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldk.api.pojo.Cart;
import com.ldk.api.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}
