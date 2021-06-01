package com.osk.team.web;

import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

  MemberService memberService;

  public AuthController(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/login")
  public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

  @RequestMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.getSession().invalidate();
    return "redirect:login";
  }

  @RequestMapping("/userInfo")
  public String userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return "/jsp/user_info.jsp";

  }
}