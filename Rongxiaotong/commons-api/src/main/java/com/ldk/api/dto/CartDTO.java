package com.ldk.api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartDTO {
    private Integer shoppingId;
    private Integer orderId;
    private Integer count;
    private String ownName;
    private Date createTime;
    private Date updateTime;


}