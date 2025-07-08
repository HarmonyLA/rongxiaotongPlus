package com.ldk.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.ldk.api.dto.OrderDTO;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Order;
import com.ldk.api.util.JWTToken;
import com.ldk.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@Tag(name = "订单接口",description = "后端9002订单接口")
public class OrderController {

    @Autowired
    OrderService  orderService;
    @Value("${aliyah.oss.serverURL}")
    private String serverURL;
    @Value("${aliyah.oss.endpoint}")
    private String endpoint;
    @Value("${aliyah.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyah.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyah.oss.bucketName}")
    private String bucketName;

    //查询热门信息
    @Operation(summary = "获取商品热门信息",description = "获取商品热门信息无需传参")
    @GetMapping("/order/findHot")
    public ResponseResult getHotOrder(){
        ResponseResult result = orderService.getHotOrders();
        return result;
    }

    // 查询所有
    @GetMapping("/order/findAll")
    @Operation(summary = "查询所有商品信息",description = "查询所有商品信息，需要两个参数")
    public ResponseResult findAll(@RequestParam(name = "currentPage",defaultValue ="1",required = false) Integer currentPage,
                                  @RequestParam(name = "pageSize",defaultValue = "20",required = false) Integer pageSize){
        ResponseResult result = orderService.getAllOrders(currentPage,pageSize);
        return  result;
    }

    // 根据id 查询数据
    @GetMapping("/order/findOrderById")
    @Operation(summary = "根据id查找订单信息",description = "不需要传参")
    public ResponseResult findOrderByID(@RequestParam("orderId") Integer orderId){
        ResponseResult result = orderService.findOrderByOrderId(orderId);
        return  result;
    }

    // 模糊查询
    @GetMapping("/order/byLikeName")
    public ResponseResult findOrderByLikeName(@RequestParam(name = "searchName")String name){
        ResponseResult result = orderService.selectByLikeName(name);
        return   result;
    }



    // 上传图片    HttpServletRequest 请求对象  springvmc框架提供的
    @PostMapping("/order/upload")
    public ResponseResult doUpLoadImage(@RequestParam("file") MultipartFile file,
                                        HttpServletRequest request){
        System.out.println(file.getOriginalFilename());

        String token = request.getHeader("token");
        System.out.println("token====="+token);
        // 配置 oss 对象存储  已完成
        // 获取原文件的名称
        String  fileName = file.getOriginalFilename();
        // 截取文件的后缀 3.1.png
        int index = fileName.lastIndexOf(".");
        // 从.开始截取到末尾
        String backStr = fileName.substring(index);//".png"
        String  beforeStr = System.currentTimeMillis()+"";
        //新的文件名
        String newFileName =  beforeStr+backStr;//
        // 连接OSS
        OSS oss = new OSSClientBuilder().build(serverURL, accessKeyId, accessKeySecret);
        ResponseResult result =null;
        // 上传文件
        try {
            oss.putObject(bucketName, newFileName, file.getInputStream());
            //返回给前端的url  https://myapp-image123.oss-cn-beijing.aliyuncs.com/文件名
            String url = "https://" + bucketName + "." + endpoint + "/" + newFileName;
            System.out.println("url=="+url);
            // 根据 token获取  用户名
            String username = JWTToken.getUserNameFromToken(token);
            Integer id = JWTToken.getIdFromToken(token);
            //将 username  和  url 存储到数据库
             result = orderService.doUpLoad(url, username,id);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            return   result;
    }




    // 发布信息
    @PostMapping("/order/updateByName")
    public  ResponseResult publishInfo(@RequestBody OrderDTO orderDTO, Order order,
                                       HttpServletRequest request){
        BeanUtils.copyProperties(orderDTO,order);
        // 还需要其他的参数
        // 根据 token获取  用户名
        String token = request.getHeader("token");
        String username = JWTToken.getUserNameFromToken(token);
        Integer id = JWTToken.getIdFromToken(token);
        order.setUserId(id)
                        .setOwnName(username);
        ResponseResult result = orderService.publishInfo(order);
        return  result;
    }

}
