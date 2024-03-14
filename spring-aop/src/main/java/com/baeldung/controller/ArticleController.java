package com.baeldung.controller;

import com.baeldung.joinpoint.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hgb/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/queryArticleList")
    public List<String> queryArticleList(@RequestParam(value = "startsWithFilter") String startsWithFilter) {
        return articleService.getArticleList(startsWithFilter);
    }

}
