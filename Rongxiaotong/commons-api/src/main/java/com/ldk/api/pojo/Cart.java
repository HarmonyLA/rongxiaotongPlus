package com.ldk.api.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_shoppingcart")
public class Cart {
    @TableId(type = IdType.AUTO)
    @TableField("shopping_id")
    private Integer shoppingId;
    @TableField("order_id")
    private Integer orderId;
    private Integer count;
    @TableField("own_name")
    private String ownName;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}
