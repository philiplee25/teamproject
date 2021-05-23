package com.osk.team.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/jsp/login_form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

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

    try {
      Member member = memberService.get(email, password);

      response.setContentType("text/html;charset=UTF-8");

      if (member == null) {
        request.getSession().invalidate();
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/jsp/login_fail.jsp").include(request, response);
        response.setHeader("Refresh", "1;url=login");

      } else {

        request.getSession().setAttribute("loginUser", member);

        response.setContentType("text/html;charset=UTF-8");
        if (member.getPower() == 0) {
          request.getRequestDispatcher("/jsp/login_success.jsp").include(request, response);
        } else if (member.getPower() == 1) {
          request.getRequestDispatcher("/jsp/login_admin_success.jsp").include(request, response);
        }
        response.setHeader("Refresh", "1;url=userInfo");

      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}