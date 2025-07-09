package com.ldk.controller;

import com.ldk.api.dto.CartDTO;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.Cart;
import com.ldk.api.util.JWTToken;
import com.ldk.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@Tag(name = "购物车接口", description = "购物车管理")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @PostMapping("/add")
    @Operation(summary = "添加商品到购物车")
    public ResponseResult addToCart(@RequestBody CartDTO item, HttpServletRequest request) {
        String token = request.getHeader("token");
        String ownName = JWTToken.getUserNameFromToken(token); // 获取用户名

        Cart cart = new Cart();
        BeanUtils.copyProperties(item, cart);
        cart.setOwnName(ownName);

        return cartService.addToCart(cart);
    }

    @GetMapping("/items")
    @Operation(summary = "获取用户购物车")
    public ResponseResult getCartItems(HttpServletRequest request) {
        String token = request.getHeader("token");
        String ownName = JWTToken.getUserNameFromToken(token);

        return cartService.getCartByUser(ownName);
    }

    @PutMapping("/update")
    @Operation(summary = "更新购物车商品数量")
    public ResponseResult updateCartItem(@RequestBody Cart cart) {
        return cartService.updateCartItem(cart);
    }

    @DeleteMapping("/remove/{shoppingId}")
    @Operation(summary = "从购物车移除商品")
    public ResponseResult removeCartItem(@PathVariable("shoppingId") Integer shoppingId) {
        return cartService.removeCartItem(shoppingId);
    }
}