package com.ldk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Cart;

public interface ShoppingCartService extends IService<Cart> {
    ResponseResult addToCart(Cart cart);
    ResponseResult getCartByUser(String ownName);
    ResponseResult updateCartItem(Cart cart);
    ResponseResult removeCartItem(Integer shoppingId);
}