package com.ldk.controller;

import com.ldk.api.json.ResponseResult;
import com.ldk.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cze/experts")
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    // 查询所有专家
    @GetMapping
    public ResponseResult getAllExperts() {
        return expertService.getAllExperts();
    }
}
