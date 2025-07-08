package com.ldk.service;

import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Cart;

public interface CartService {
    ResponseResult addToCart(Cart cart);
    ResponseResult getCartList(String username);
    ResponseResult updateCartItem(Cart cart);
    ResponseResult deleteCartItem(Integer shoppingId);
}