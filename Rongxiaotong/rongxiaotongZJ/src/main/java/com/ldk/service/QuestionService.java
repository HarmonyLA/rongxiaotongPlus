package com.ldk.service;

import com.ldk.api.json.ResponseResult;

public interface QuestionService {
    ResponseResult searchByTitle(String keyword);

    ResponseResult getAllQuestions(Integer currentPage, Integer pageSize);
}
