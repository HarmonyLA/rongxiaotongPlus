package com.ldk.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@TableName("questions")
@Accessors(chain = true)
public class Question {
    private Integer id;
    private String title;
    private String content;
    @TableField(exist = false)
    private String type; // needs/other
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}