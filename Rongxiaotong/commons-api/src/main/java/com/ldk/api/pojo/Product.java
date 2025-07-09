package com.ldk.api.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data  // Lombok 注解，自动生成 getter/setter/toString 等
@TableName("tb_product")  // 绑定数据库表名
public class Product {
    @TableId(type = IdType.AUTO)  // 主键自增
    private Integer id;            // 商品ID（主键）

    private String title;          // 需求标题
    private BigDecimal price;      // 价格（精度10，小数位2）
    private String content;        // 内容

    private Integer status;        // 商品状态：0待合作，1待发货
    @TableField("product_status")  // 映射数据库字段 product_status
    private Integer productStatus; // 商品上下架：0已上架，1已下架
    private Integer type;          // 订单类型：1销售订单，2需求订单

    private String picture;        // 图片地址
    @TableField("demand_initiator")
    private String demandInitiator;// 需求发起人
    @TableField("cooperation_name")
    private String cooperationName;// 合作人名字

    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间

    @TableField("address_id") //
    private String addressId;

    @TableField(exist = false) // 非数据库字段
    private String address;


    public String getAddress() {
        return address;
    }
}