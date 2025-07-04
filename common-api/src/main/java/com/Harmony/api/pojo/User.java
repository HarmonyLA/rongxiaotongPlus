package com.Harmony.api.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @TableName("tb_user")
    public class User implements Serializable {
        @TableId(type = IdType.AUTO)
        private Integer id;
        @TableId
        @TableField("user_name")
        //@Pattern
        private String userName;
        private String password;
        @TableField("nick_name")
        private String nickName;
        @TableField("real_name")
        private String realName;
        private String phone;
        @TableField("identity_num")
        private String identityNum;// 身份证
        private String address;
        private String role;
        @TableField("create_time")
        private Date createTime;
        @TableField("update_time")
        private Date updateTime;
        private int integral;// 积分
        private int credit;// 信誉
        private String avatar;// 头像
        @TableField(exist = false)
        private String token;

    }

