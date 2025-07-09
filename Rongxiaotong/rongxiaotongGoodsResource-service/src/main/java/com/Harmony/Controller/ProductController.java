package com.Harmony.Controller;

import com.Harmony.Service.ProductService;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;
import java.util.List;

@RestController
@RequestMapping("/api/products")

@Tag(name = "产品管理API", description = "提供产品搜索和详情查询功能")
public class ProductController {

    @Resource
    private ProductService productService;

    @Operation(summary = "这个方法是用于根据用户的需求查询出相关的订单信息（eg：用户查询水蜜桃  会返回相关的一列表的供应信息）")
    @GetMapping("/search")
    @Parameters({
            @Parameter(name = "token",description = "请求token",required = true,in = ParameterIn.HEADER),
            @Parameter(name = "keyword",description = "搜索关键词",required = true,in = ParameterIn.QUERY),
    })
    public ResponseResult searchProducts(@RequestParam("keyword") String keyword) {
        List<Product> productList = productService.searchByTitle(keyword);
        return new ResponseResult()
            .setCode(200)
            .setMessage("查询成功")
            .setData(productList);
    }
    @Operation(summary = "这个方法用于根据产品ID查询具体的产品详情信息（eg：用户查询ID为1001的产品，会返回该产品的完整信息）")
    @GetMapping("/detail")
    @Parameters({
            @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "id", description = "产品ID（用于唯一标识某个产品）", required = true, in = ParameterIn.QUERY)
    })
    public ResponseResult detail(@RequestParam("id") String id) {
        return productService.getProductById(id);
    }


}    