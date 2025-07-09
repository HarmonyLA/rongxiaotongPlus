package com.Harmony.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldk.api.pojo.Product;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}