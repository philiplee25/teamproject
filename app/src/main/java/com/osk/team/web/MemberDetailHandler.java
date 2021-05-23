package com.osk.team.web;

import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/detail")
public class MemberDetailHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Member m = memberService.get(no);
      request.setAttribute("member", m);
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/jsp/member/detail.jsp").include(request, response);
      //
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}







