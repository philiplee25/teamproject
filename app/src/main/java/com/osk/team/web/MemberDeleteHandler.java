package com.osk.team.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;

@Controller
public class MemberDeleteHandler {

  MemberService memberService;

  public MemberDeleteHandler(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/delete")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Member member = memberService.get(no);
    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (member.getNo() != loginUser.getNo()) {
      throw new Exception("삭제 권한이 없습니다!");
    }

    memberService.delete(no);

    return "redirect:list";

  }
}

