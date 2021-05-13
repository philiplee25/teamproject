package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginHandler extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title> 로그인 </title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1> 로그인하기 </h1>");
    out.println("<form action='add' method='post'>");

    out.println("이메일: <input type='text' name='email'><br>");
    out.println("비밀번호: <input type='number' name='password'><br>");

    String email = request.getParameter("email");
    String password = request.getParameter("password");


    out.println("<input type='submit' value='등록'>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");

    try {
      Member member = memberService.get(email, password);
      if (member == null) {
        out.println("사용자 정보가 맞지 않습니다.");
        // 로그인 실패한다면 세션 객체의 모든 내용을 삭제한다.
        request.getSession().invalidate();
        return;
      }
      if (member.getPower() == 0) {//일반 회원
        // 로그인 성공한다면, 로그인 사용자 정보를 세션 객체에 보관한다.

        request.getSession().setAttribute("loginUser", member);
        out.printf("%s 님 환영합니다.\n", member.getName());
      } else if (member.getPower() == 1) {
        // 로그인 성공한다면, 로그인 사용자 정보를 세션 객체에 보관한다.
        request.getSession().setAttribute("loginUser", member);
        out.println("관리자님 환영합니다!\n");
      }
    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);
      out.println(strWriter.toString());
    }
  }
}