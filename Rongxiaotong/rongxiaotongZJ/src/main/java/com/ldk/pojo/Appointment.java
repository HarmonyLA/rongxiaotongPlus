package com.ldk.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("appointments")
@Accessors(chain = true)
public class Appointment {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("expert_id")
    private Integer expertId;

    private String address;

    private String crop;

    @TableField("crop_detail")
    private String cropDetail;

    @TableField("crop_condition")
    private String cropCondition;

    @TableField("soil_condition")
    private String soilCondition;

    private BigDecimal area;

    @TableField("user_phone")
    private String userPhone;

    @TableField("appointment_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date appointmentTime;

    private String status;

}
