package com.ldk.api.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_order")
public class Order {
    @TableId(type = IdType.AUTO)
    @TableField("order_id")
    private Integer orderId;
    private String title;
    private String price;
    private String content;
    @TableField("order_status")
    private Integer orderStatus;
    private String type;
    private String picture;
    @TableField("own_name")
    private String ownName;
    @TableField("cooperation_name")
    private String cooperationName;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    private String address;
    @TableField("user_id")
    private Integer  userId;
}
