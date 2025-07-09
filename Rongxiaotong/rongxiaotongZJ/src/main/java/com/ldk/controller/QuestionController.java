package com.ldk.controller;

import com.ldk.api.json.ResponseResult;
import com.ldk.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/cze/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // 模糊查询问题标题
    @GetMapping("/search")
    public ResponseResult searchByTitle(
            @RequestParam(name = "keyword", required = true) String keyword) {
        return questionService.searchByTitle(keyword);
    }

    // 查询所有问题（带分页）
    @GetMapping
    public ResponseResult getAllQuestions(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return questionService.getAllQuestions(currentPage, pageSize);
    }
}
