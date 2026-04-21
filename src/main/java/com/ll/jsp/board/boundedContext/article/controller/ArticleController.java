package com.ll.jsp.board.boundedContext.article.controller;

import com.ll.jsp.board.boundedContext.article.dto.Article;
import com.ll.jsp.board.boundedContext.article.service.ArticleService;
import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.global.base.Rq;

import java.util.List;

public class ArticleController {
    private final ArticleService articleService;

    public ArticleController() {
        articleService = Container.articleService;
    }

    public void showList(Rq rq) {
        List<Article> articleList = articleService.findAll();

        System.out.println(articleList);
        rq.setAttr("articleList", articleList);
        rq.view("usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String content = rq.getParam("content", "");
        long id = articleService.write(title, content);

        rq.appendBody("""
                글 작성 완료
                <div>%d 게시물 생성</div>
                <div>제목 : %s</div>
                <div>내용 : %s</div>
                <a href="/usr/article/list">목록으로</a>
                """.formatted(id, title, content));
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id <= 0) {
            rq.appendBody("""
                    <script>
                        alert('올바른 요청이 아닙니다.');
                        history.back();
                    </script>
                    """);
            return;
        }

        Article article = articleService.findById(id);

        if (article == null) {
            rq.appendBody("""
                    <script>
                        alert("%d번 게시물이 존재하지 않습니다.");
                        history.back();
                    </script>
                    """.formatted(id));
            return;
        }

        rq.setAttr("article", article);
        rq.view("usr/article/detail");
    }

    public void showModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        if (id <= 0) {
            rq.appendBody("""
                    <script>
                        alert('올바른 요청이 아닙니다.');
                        history.back();
                    </script>
                    """);
            return;
        }

        Article article = articleService.findById(id);

        if (article == null) {
            rq.appendBody("""
                    <script>
                        alert("%d번 게시물이 존재하지 않습니다.");
                        history.back();
                    </script>
                    """.formatted(id));
            return;
        }

        rq.setAttr("article", article);
        rq.view("usr/article/modify");
    }

    public void doModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        String title = rq.getParam("title", "");

        if (title.isBlank()) {
            rq.appendBody("""
                    <script>
                        alert('제목을 입력해주세요.');
                        history.back();
                    </script>
                    """);
            return;
        }

        String content = rq.getParam("content", "");

        if (title.isBlank()) {
            rq.appendBody("""
                    <script>
                        alert('내용을 입력해주세요.');
                        history.back();
                    </script>
                    """);
            return;
        }

        articleService.modify(id, title, content);

        rq.appendBody("""
                <div>%d 게시물 수정</div>
                <div>제목 : %s</div>
                <div>내용 : %s</div>
                <a href="/usr/article/list">목록으로</a>
                """.formatted(id, title, content));
    }
}
