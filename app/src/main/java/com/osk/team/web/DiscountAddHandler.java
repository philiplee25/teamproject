package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Discount;
import com.osk.team.service.DiscountService;

@SuppressWarnings("serial")
public class DiscountAddHandler extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    DiscountService discountService = (DiscountService) request.getServletContext().getAttribute("discountService");

    Discount d = new Discount();

    d.setTitle(request.getParameter("title"));
    d.setContent(request.getParameter("content"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시글 등록</title>");

    try {
      discountService.add(d);

      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>할인정보 등록</h1>");
      out.println("<p>할인정보를 등록했습니다.</p>");

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      out.println("</head>");
      out.println("<body>");
      out.println("<h1>할인정보 등록 오류</h1>");
      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<p><a href='list'>목록</a></p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}