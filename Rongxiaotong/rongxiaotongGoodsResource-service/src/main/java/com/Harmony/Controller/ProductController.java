package com.Harmony.Controller;

import com.Harmony.Service.ProductService;
import com.ldk.api.dto.ProductDTO;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Product;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/search")
    public ResponseResult searchProducts(@RequestParam("keyword") String keyword) {
        List<Product> productList = productService.searchByTitle(keyword);
        return new ResponseResult()
            .setCode(200)
            .setMessage("查询成功")
            .setData(productList);
    }
    @GetMapping("/detail")
    public ResponseResult detail(@RequestParam("id") String id) {
        return productService.getProductById(id);
    }
}    