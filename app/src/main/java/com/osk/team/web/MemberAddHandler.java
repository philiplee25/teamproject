package com.osk.team.web;
// 회원가입

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/Join/add")
public class JoinAddHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title> 회원가입 </title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1> 회원가입하기 </h1>");
    out.println("<form action='add' method='post'>");
    out.println("이름: <input type='text' name='name'><br>");
    out.println("이메일: <input type='text' name='email'><br>");
    out.println("비밀번호: <input type='number' name='password'><br>");
    out.println("성별: <input type='text' name='gender'><br>");
    out.println("생일: <input type='date' name='birth'><br>");
    out.println("휴대폰번호: <input type='number' name='tel'><br>");
    out.println("주소: <textarea name='address' rows='10' cols='60'></textarea><br>"); // member 에 주소가 빠져있나?

    try {
      List<Member> members = memberService.list();
      for (Member m : members) {
        out.printf("  <input type='checkbox' name='member' value='%d'>%s<br>\n", m.getNo(), m.getName());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("<input type='submit' value='등록'>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberService MemberService = (MemberService) request.getServletContext().getAttribute("MemberService");

    // 클라이언트가 POST 요청으로 보낸 데이터가 UTF-8임을 알려준다.
    request.setCharacterEncoding("UTF-8");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>회원가입 </title>");

    try {
      Join J = new Join;
      J.setName(request.getParameter("name"));
      J.setEmail(request.getParameter("email"));
      J.setPassword(Integer.parseInt(request.getParameter("password")));
      J.setGender(request.getParameter("gender"));
      J.setBirth(Date.valueOf(request.getParameter("Birth")));
      J.setTel(Integer.parseInt(request.getParameter("tel")));
      J.setAddress(request.getParameter("address"));

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      J.setOwner(loginUser);


      String[] values = request.getParameterValues("member");
      ArrayList<Member> memberList = new ArrayList<>();
      for (String value : values) {
        Member member = new Member();
        member.setNo(Integer.parseInt(value));
        memberList.add(member);
      }
      J.setMembers(memberList);

      JoinService.add(J);

      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>회원가입 완료!</h1>");
      out.println("<p>회원가입을 완료했습니다.</p>");

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      out.println("</head>");
      out.println("<body>");
      out.println("<h1>회원가입 실패!</h1>");
      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<p><a href='list'>목록보기</a></p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
