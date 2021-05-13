package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Hotplace;
import com.osk.team.service.HotplaceService;

@SuppressWarnings("serial")
@WebServlet("/hotplace/add")
public class HotplaceAddHandler extends HttpServlet {


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HotplaceService clubService =
        (HotplaceService) request.getServletContext().getAttribute("hotplaceService");

    Hotplace h = new Hotplace();

    request.setCharacterEncoding("UTF-8");
    // private int no;//게시판 번호
    // private String title;//게시판 제목
    // private String content;//게시판 내용
    // private Date date;//등록 날짜
    // private int count;//조회수
    // private String photo;//사진


    h.setAddress(request.getParameter("address"));
    h.setTitle(request.getParameter("title"));
    h.setContent(request.getParameter("content"));
    h.setDate(Date.valueOf(request.getParameter("date")));



    HttpServletRequest httpRequest = request;
    int loginUser = (int) httpRequest.getSession().getAttribute("loginUser");// 회원번호로 받기
    h.setNo(loginUser);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>핫플레이스 등록</title>");

    try {
      // HotplaceService.add(h);

      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>핫플레이스 등록</h1>");
      out.println("<p>핫플레이스 등록했습니다.</p>");
    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      out.println("</head>");
      out.println("<body>");
      out.println("<h1>핫플레이스 등록 오류</h1>");
      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<p><a href='list'>핫플레이스 목록</a></p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
