package com.osk.team.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;

@Controller
public class LoginHandler {

  MemberService memberService;

  public LoginHandler(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/login")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    if (request.getMethod().equals("GET")) {
      return "/jsp/login_form.jsp";
    }

    String email = request.getParameter("email");
    String password = request.getParameter("password");


    if (request.getParameter("saveEmail") != null) {
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 5);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member member = memberService.get(email, password);

    if (member == null) {
      request.getSession().invalidate();
      return "/jsp/login_fail.jsp";
    } else {
      request.getSession().setAttribute("loginUser", member);

      if (member.getPower() == 0) {
        return "/jsp/login_success.jsp";
      } else if (member.getPower() == 1) {
        return "/jsp/login_admin_success.jsp";
      }
      return "/jsp/login_success.jsp";
    }
  }
}