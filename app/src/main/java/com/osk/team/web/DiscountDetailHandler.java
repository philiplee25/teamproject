package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Discount;
import com.osk.team.service.DiscountService;

@SuppressWarnings("serial")
@WebServlet("/discount/Detail")
public class DiscountDetailHandler extends HttpServlet {

  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    DiscountService discountService = (DiscountService) request.getServletContext().getAttribute("discountService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    int no = Integer.parseInt(request.getParameter("no"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>할인정보 상세보기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>할인정보 상세보기</h1>");

    try {
      Discount d = discountService.get(no);
      if (d == null) {
        out.println("<p>해당 번호의 할인정보가 없습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }
      out.println("<form action='update' method='post'>");
      out.println("<table border='1'>");
      out.println("<tbody>");
      out.printf("<tr><th>번호</th>"
          + " <td><input type='text' name='no' value='%d' readonly></td></tr>\n", d.getNo());
      out.printf("<tr><th>제목</th>"
          + " <td><input name='title' type='text' value='%s'></td></tr>\n", d.getTitle());
      out.printf("<tr><th>내용</th>"
          + " <td><textarea name='content' rows='10' cols='60'>%s</textarea></td></tr>\n", d.getContent());
      out.printf("<tr><th>등록일</th> <td>%s</td></tr>\n", formatter.format(d.getDate()));
      out.println("</tbody>");

      out.println("</table>");
      out.println("</form>");


    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);
      out.printf("<pre>%s</pre>\n", strWriter.toString());
    }
    out.println("<p><a href='list'>목록</a></p>");

    out.println("</body>");
    out.println("</html>");
  }
}