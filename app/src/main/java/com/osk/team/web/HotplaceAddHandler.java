package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>핫플레이스</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>핫플레이스</h1>");
    out.println("<form action='add' method='post'>");
    out.println("제목: <input type='text' name='title'><br>");
    out.println("내용: <textarea name='content' rows='10' cols='60'></textarea><br>");
    out.println("<input type='submit' value='등록'>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HotplaceService hotplaceService =
        (HotplaceService) request.getServletContext().getAttribute("boardService");

    Hotplace h = new Hotplace();

    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); // 날짜 포멧 형식 지정 및 생성
    // System.out.println(sdf.format(d)); // 위에서 지정한 포멧 형식으로 날짜 출력

    request.setCharacterEncoding("UTF-8");

    h.setTitle(request.getParameter("title"));
    h.setContent(request.getParameter("content"));
    h.setDate(sdf.format(d));
    h.setCount(1);
    h.setAddress(request.getParameter("address"));
    h.setAddress(request.getParameter("photo"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>핫플레이스 등록</title>");

    try {
      hotplaceService.add(h);

      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>핫플레이스 등록</h1>");
      out.println("<p>핫플레이스를 등록했습니다.</p>");

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      out.println("</head>");
      out.println("<body>");
      out.println("<h1>핫플레이스 등록오류</h1>");
      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<p><a href='list'>목록</a></p>");

    }

    out.println("</body>");
    out.println("</html>");
  }
}


