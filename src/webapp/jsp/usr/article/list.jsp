<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ll.jsp.board.boundedContext.article.dto.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    %>

<h1>게시물 리스트</h1>

<div>
    <ul>
        <% for (Article article : articleList) { %>
        <li><%= article.getId() %>번 : <%= article.getTitle() %></li>
        <% } %>
    </ul>
</div>