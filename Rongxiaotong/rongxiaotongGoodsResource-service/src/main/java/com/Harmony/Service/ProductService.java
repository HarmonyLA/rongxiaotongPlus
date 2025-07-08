package com.Harmony.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    List<Product> searchByTitle(String keyword);
    ResponseResult getProductById(String id);
}    