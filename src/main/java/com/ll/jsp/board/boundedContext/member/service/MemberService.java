package com.ll.jsp.board.boundedContext.member.service;

import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.global.base.Rq;
import com.ll.jsp.board.boundedContext.member.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService() {
        this.memberRepository = Container.memberRepository;
    }

    public void showJoin(Rq rq) {
        rq.view("/usr/member/join");
    }
}