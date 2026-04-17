package com.ll.jsp.board.boundedContext.global;
import com.ll.jsp.board.boundedContext.article.controller.ArticleController;
import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.global.base.Rq;
import com.ll.jsp.board.boundedContext.member.controller.MemberController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req, resp);

        MemberController memberController = Container.memberController;
        ArticleController articleController = Container.articleController;

        String url = req.getRequestURI();

        switch (url) {
            case "/usr/article/list" -> articleController.showList(rq);
            case "/usr/member/join" -> memberController.showJoin(rq);
        }
    }
}