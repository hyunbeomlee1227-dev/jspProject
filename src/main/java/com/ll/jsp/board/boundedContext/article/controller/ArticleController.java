package com.ll.jsp.board.boundedContext.article.controller;

import com.ll.jsp.board.boundedContext.article.dto.Article;
import com.ll.jsp.board.boundedContext.global.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class ArticleController {
    private final List<Article> articleList;

    public ArticleController() {
        articleList = new ArrayList<>();
        makeTestData();
    }

    void makeTestData() {
        LongStream.rangeClosed(1, 5).forEach(i -> {
            Article article = new Article(i, "제목 " + i, "내용 " + i);
            articleList.add(article);
        });
    }

    public void showList(Rq rq) {
        rq.setAttr("articleList", articleList);
        rq.view("usr/article/list");
    }
}
