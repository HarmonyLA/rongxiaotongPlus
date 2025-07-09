package com.ldk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Cart;
import com.ldk.mapper.ShoppingCartMapper;
import com.ldk.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, Cart>
        implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper cartMapper;

    @Override
    public ResponseResult addToCart(Cart cart) {
        cart.setCreateTime(new Date());
        cart.setUpdateTime(new Date());

        // 检查是否已存在相同商品
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("own_name", cart.getOwnName())
                .eq("order_id", cart.getOrderId());

        Cart existing = this.getOne(wrapper);
        if (existing != null) {
            existing.setCount(existing.getCount() + cart.getCount());
            existing.setUpdateTime(new Date());
            this.updateById(existing);
            return new ResponseResult(200, "购物车已更新", existing, null);
        } else {
            this.save(cart);
            return new ResponseResult(200, "已加入购物车", cart, null);
        }
    }

    @Override
    public ResponseResult getCartByUser(String ownName) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("own_name", ownName);
        List<Cart> cartItems = cartMapper.selectList(wrapper);
        return new ResponseResult(200, "SUCCESS", cartItems, null);
    }

    @Override
    public ResponseResult updateCartItem(Cart cart) {
        cart.setUpdateTime(new Date());
        this.updateById(cart);
        return new ResponseResult(200, "购物车已更新", cart, null);
    }

    @Override
    public ResponseResult removeCartItem(Integer shoppingId) {
        this.removeById(shoppingId);
        return new ResponseResult(200, "已从购物车移除", null, null);
    }
}