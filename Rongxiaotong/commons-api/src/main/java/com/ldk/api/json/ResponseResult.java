package com.ldk.api.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseResult implements Serializable {
    private Integer  code;// 表示状态码 200表示成功 非200 表示故障或者失败
    private String message;//表示返回给前端的提示内容  "登录成功'
    private Object data;//表示前端需要展示的数据内容
    private Object pageData;
}
