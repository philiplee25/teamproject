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
import com.osk.team.domain.Qna;
import com.osk.team.service.QnaService;

@SuppressWarnings("serial")
@WebServlet("/qna/update")
public class QnaUpdateHandler extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    QnaService qnaService = (QnaService) request.getServletContext().getAttribute("qnaService");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>QnA 변경</title>");
    try {
      request.setCharacterEncoding("UTF-8");
      int no = Integer.parseInt(request.getParameter("no"));
      Qna oldQna = qnaService.get(no);
      if (oldQna == null) {
        throw new Exception("해당 번호의 QnA가 없습니다.");
      }
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (loginUser.getPower() == 0 && oldQna.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("변경 권한이 없습니다!");
      }

      Qna qna = new Qna();
      qna.setNo(oldQna.getNo());
      qna.setTitle(request.getParameter("title"));
      qna.setContent(request.getParameter("content"));
      qna.setAnswer(request.getParameter("answer"));
      qnaService.update(qna);

      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>QnA 변경</h1>");
      out.println("<p>QnA을 변경하였습니다.</p>");


    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>QnA 변경 오류</h1>");
      out.printf("<p>%s</p>\n", e.getMessage());
      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<p><a href='list'>목록</a></p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}