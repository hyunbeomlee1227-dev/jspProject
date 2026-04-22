package com.ll.jsp.board.boundedContext.member.controller;

import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.global.base.Rq;
import com.ll.jsp.board.boundedContext.member.dto.Member;
import com.ll.jsp.board.boundedContext.member.service.MemberService;

public class MemberController {
    private final MemberService memberService;

    public MemberController() {
        memberService = Container.memberService;
    }

    public void showJoin(Rq rq) {
        rq.view("usr/member/join");
    }

    public void doJoin(Rq rq) {
        String username = rq.getParam("username", "");
        String password = rq.getParam("password", "");
        String name = rq.getParam("name", "");

        memberService.join(username, password, name);

        rq.replace("%s 님 회원가입이 완료 되었습니다.".formatted(username), "/");
    }

    public void showLogin(Rq rq) {
        rq.view("usr/member/login");
    }

    public void doLogin(Rq rq) {
        String username = rq.getParam("username", "");

        if (username.trim().isBlank()) {
            rq.replace("로그인 아이디를 입력해주세요.", "/usr/member/join");
            return;
        }

        String password = rq.getParam("password", "");

        if (password.trim().isBlank()) {
            rq.replace("비밀번호를 입력해주세요.", "/usr/member/join");
            return;
        }

        Member member = memberService.findByUsername(username);

        if (member == null) {
            rq.replace("해당 회원은 존재하지 않습니다.", "/usr/member/login");
            return;
        }

        if (!member.getPassword().equals(password)) {
            rq.replace("비밀번호가 일치하지않습니다.", "/usr/member/login");
            return;
        }

        rq.setSessionAttr("loggedInMember", member);

        rq.replace("%s 님 로그인 되었습니다. 반갑습니다.".formatted(username), "/");
    }

    public void showMe(Rq rq) {
        Member member = (Member) rq.getSessionAttr("loggedInMember");
        rq.print("로그인된 회원 : " + member.getUsername());
    }

    public void doLogout(Rq rq) {
        rq.removeSessionAttr("loggedInMember");

        rq.replace("로그아웃 되었습니다.", "/");
    }
}

