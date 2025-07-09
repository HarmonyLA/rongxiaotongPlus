package com.ldk.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private Integer id;            // 商品ID
    private String title;          // 需求标题
    private BigDecimal price;      // 价格
    private String content;        // 内容

    private Integer status;        // 商品状态：0待合作，1待发货
    private String statusDesc;     // 状态描述（前端友好展示）
    private Integer productStatus; // 上下架状态：0已上架，1已下架
    private String productStatusDesc; // 上下架描述
    private Integer type;          // 订单类型：1销售订单，2需求订单
    private String typeDesc;       // 订单类型描述

    private String picture;        // 图片地址
    private String demandInitiator;// 需求发起人
    private String cooperationName;// 合作人名字

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime; // 创建时间（格式化后）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime; // 修改时间（格式化后）

    // 【可选】通过 setter 自动生成状态描述（也可在业务层处理）
    public void setStatus(Integer status) {
        this.status = status;
        if (status == 0) this.statusDesc = "待合作";
        else if (status == 1) this.statusDesc = "待发货";
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
        if (productStatus == 0) this.productStatusDesc = "已上架";
        else if (productStatus == 1) this.productStatusDesc = "已下架";
    }

    public void setType(Integer type) {
        this.type = type;
        if (type == 1) this.typeDesc = "销售订单";
        else if (type == 2) this.typeDesc = "需求订单";
    }
}