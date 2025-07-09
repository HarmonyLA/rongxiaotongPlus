package com.ldk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldk.api.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<Cart> {
}