package com.ldk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldk.api.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
