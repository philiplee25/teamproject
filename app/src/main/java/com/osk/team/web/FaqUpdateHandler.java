package com.osk.team.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osk.team.domain.Faq;
import com.osk.team.domain.Member;
import com.osk.team.service.FaqService;

@SuppressWarnings("serial")
@WebServlet("/faq/update")
public class FaqUpdateHandler extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    FaqService faqService = (FaqService) request.getServletContext().getAttribute("faqService");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Faq oldFaq = faqService.get(no);
      if (oldFaq== null) {
        throw new Exception("해당 번호의 FAQ가 없습니다.");
      }

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (1 != loginUser.getPower()) {
        throw new Exception("변경 권한이 없습니다!");
      }

      Faq f = new Faq();
      f.setNo(oldFaq.getNo());
      f.setTitle(request.getParameter("title"));
      f.setContent(request.getParameter("content"));

      faqService.update(f);

      response.sendRedirect("list");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}