package com.Harmony.Service.impl;

import com.Harmony.Mapper.AddressMapper;
import com.Harmony.Mapper.ProductMapper;
import com.Harmony.Service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Address;
import com.ldk.api.pojo.Product;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;
    @Resource
    private AddressMapper  addressMapper;

    @Override
    public List<Product> searchByTitle(String keyword) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.like("title", keyword).like("type",1);
        return productMapper.selectList(wrapper);
    }

    @Override
    public ResponseResult getProductById(String id) {
            int pid = Integer.parseInt(id);
        System.out.println("id="+id);
        System.out.println("pid="+pid);
            Product product = productMapper.selectById(pid);
        System.out.println(product.toString());
            QueryWrapper<Address> wrapper = new QueryWrapper<>();
            wrapper.eq("id",product.getAddressId() );
            product.setAddress(addressMapper.selectOne(wrapper).getAddressDetail());
            return new ResponseResult(200,"SUCCESS",product,null);
    }

}    