package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Faq;
import com.osk.team.service.FaqService;

@SuppressWarnings("serial")
@WebServlet("/faq/search")
public class FaqSearchHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String keyword = request.getParameter("keyword");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>FAQ 검색</title>");
    out.println("</head>");
    out.println("<body>");
    out.printf("<h1>게시글 검색 결과 : %s</h1>\n", keyword);

    try {
      if (keyword == null || keyword.length() == 0) {
        throw new SearchException("검색어를 입력하세요.");
      }

      FaqService faqService = (FaqService) request.getServletContext().getAttribute("faqService");
      List<Faq> list = faqService.search(keyword);
      if (list.size() == 0) {
        throw new SearchException("검색어에 해당하는 게시글이 없습니다.");
      }

      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr>");
      out.println("<th>번호</th> <th>제목</th> <th>내용</th> <th>등록일</th>");
      out.println("</tr>");
      out.println("</thead>");
      out.println("<tbody>");

      for (Faq f : list) {
        out.printf("<tr>"
            + " <td>%d</td>"
            + " <td><a href='detail?no=%1$d'>%s</a></td>"
            + " <td>%s</td>"
            + " <td>%s</td> </tr>\n", 
            f.getNo(), 
            f.getTitle(), 
            f.getDate(),
            f.getContent());
      }
      out.println("</tbody>");
      out.println("</table>");

    } catch (SearchException e) {
      out.printf("<p>%s</p>\n", e.getMessage());

    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }
}






