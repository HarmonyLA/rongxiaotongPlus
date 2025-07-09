package com.ldk.api.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_address") // 假设数据库表名为 `address`（需与实际表名一致）
public class Address {
    /**
     * 主键ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关联用户标识（如用户ID，表字段：own_name）
     */
    @TableField("own_name")
    private String ownName;

    /**
     * 收货人姓名
     */
    private String consignee;

    /**
     * 收货人电话
     */
    private String phone;

    /**
     * 收货地址详情（表字段：address_detail）
     */
    @TableField("address_detail")
    private String addressDetail;

    /**
     * 是否默认地址：1=是，0=否（表字段：is_default，允许空时需处理默认值）
     * 注意：若数据库默认值为1，可在代码中初始化：private Integer isDefault = 1;
     */
    @TableField("is_default")
    private Integer isDefault;
}