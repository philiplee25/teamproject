package com.osk.team.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osk.team.domain.Faq;
import com.osk.team.service.FaqService;

@SuppressWarnings("serial")
@WebServlet("/faq/detail")
public class FaqDetailHandler extends HttpServlet {

  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    FaqService faqService = (FaqService) request.getServletContext().getAttribute("faqService");

    response.setContentType("text/html;charset=UTF-8");

    int no = Integer.parseInt(request.getParameter("no"));

    try {
      Faq faq = faqService.get(no);

      request.setAttribute("faq", faq);

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/jsp/faq/detail.jsp").include(request, response);



    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}