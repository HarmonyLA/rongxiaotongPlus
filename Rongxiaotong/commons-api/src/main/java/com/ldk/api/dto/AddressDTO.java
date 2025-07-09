package com.ldk.api.dto;
import com.ldk.api.pojo.Address;
import lombok.Data;

@Data
public class AddressDTO {
    /**
     * 地址ID
     */
    private Integer id;

    /**
     * 关联用户标识（同POJO）
     */
    private String ownName;

    /**
     * 收货人姓名
     */
    private String consignee;

    /**
     * 收货人电话（可添加脱敏处理，如隐藏中间4位）
     */
    private String phone;

    /**
     * 收货地址详情
     */
    private String addressDetail;

    /**
     * 是否默认地址（转换为布尔值，更友好）
     * true=是（1），false=否（0或null）
     */
    private Boolean isDefault;

    // ========== 扩展：POJO 转 DTO 的工具方法 ==========
    public static AddressDTO fromPOJO(Address pojo) {
        AddressDTO dto = new AddressDTO();
        dto.setId(pojo.getId());
        dto.setOwnName(pojo.getOwnName());
        dto.setConsignee(pojo.getConsignee());
        // 可选：电话脱敏，如 138****1234
        dto.setPhone(desensitizePhone(pojo.getPhone()));
        dto.setAddressDetail(pojo.getAddressDetail());
        // 转换 tinyint(1/0) 为 Boolean
        dto.setIsDefault(pojo.getIsDefault() != null && pojo.getIsDefault() == 1);
        return dto;
    }

    /**
     * 电话脱敏逻辑（示例）
     */
    private static String desensitizePhone(String phone) {
        if (phone.length() == 11) {
            return phone.substring(0, 3) + "****" + phone.substring(7);
        }
        return phone;
    }
}