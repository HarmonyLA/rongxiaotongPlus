package com.ldk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Cart;
import com.ldk.mapper.CartMapper;
import com.ldk.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public ResponseResult addToCart(Cart cart) {
        // 检查是否已存在相同商品
        Cart existing = cartMapper.selectOne(new QueryWrapper<Cart>()
                .eq("own_name", cart.getOwnName())
                .eq("order_id", cart.getOrderId()));

        if (existing != null) {
            existing.setCount(existing.getCount() + cart.getCount());
            existing.setUpdateTime(new Date());
            cartMapper.updateById(existing);
            return new ResponseResult(200, "商品数量已更新", null, null);
        }
        if (cart.getCount() == null || cart.getCount() < 1) {
            cart.setCount(1);  // 设置默认数量
        }

        cart.setCreateTime(new Date());
        cart.setUpdateTime(new Date());
        cartMapper.insert(cart);
        return new ResponseResult(200, "添加成功", null, null);
    }

    @Override
    public ResponseResult getCartList(String username) {
        List<Cart> cartList = cartMapper.selectList(new QueryWrapper<Cart>()
                .eq("own_name", username));
        return new ResponseResult(200, "SUCCESS", cartList, null);
    }

    @Override
    public ResponseResult updateCartItem(Cart cart) {
        cart.setUpdateTime(new Date());
        cartMapper.updateById(cart);
        return new ResponseResult(200, "更新成功", null, null);
    }

    @Override
    public ResponseResult deleteCartItem(Integer shoppingId) {
        cartMapper.deleteById(shoppingId);
        return new ResponseResult(200, "删除成功", null, null);
    }
}