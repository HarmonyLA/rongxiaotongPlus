package com.ldk.controller;

import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Cart;
import com.ldk.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseResult addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    @GetMapping("/list")
    public ResponseResult getCartList(@RequestParam("username") String username) {
        return cartService.getCartList(username);
    }

    @PutMapping("/update")
    public ResponseResult updateCartItem(@RequestBody Cart cart) {
        return cartService.updateCartItem(cart);
    }

    @DeleteMapping("/delete/{shoppingId}")
    public ResponseResult deleteCartItem(@PathVariable("shoppingId") Integer shoppingId) {
        return cartService.deleteCartItem(shoppingId);
    }
}