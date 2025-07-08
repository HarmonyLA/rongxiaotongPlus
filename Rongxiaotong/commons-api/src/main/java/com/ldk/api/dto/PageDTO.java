package com.ldk.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class PageDTO {
    private  long total;
    private  long pages;
    private  long current;
    private  long size;
}
