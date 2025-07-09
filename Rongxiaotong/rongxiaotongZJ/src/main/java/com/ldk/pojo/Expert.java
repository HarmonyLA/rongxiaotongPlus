package com.ldk.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("experts")
@Accessors(chain = true)
public class Expert {
    private Integer id;
    private String name;
    private String specialty;
}