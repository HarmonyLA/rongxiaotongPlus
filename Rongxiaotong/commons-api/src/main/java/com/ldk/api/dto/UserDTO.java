package com.ldk.api.dto;


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
public class UserDTO implements Serializable {
    private Integer id;
    //@Pattern
    private String userName;
    private String password;
    private String nickName;
    private String realName;
    private String phone;
    private String identityNum;// 身份证
    private String address;
    private String role;
    private Date createTime;
    private Date updateTime;
    private Integer integral;// 积分
    private Integer credit;// 信誉
    private String avatar;// 头像
    private String token;

}
