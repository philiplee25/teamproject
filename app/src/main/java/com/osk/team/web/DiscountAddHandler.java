package com.osk.team.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Discount;
import com.osk.team.domain.Member;
import com.osk.team.service.DiscountService;

@SuppressWarnings("serial")
@WebServlet("/discount/add")
public class DiscountAddHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/jsp/discount/form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    DiscountService discountService = (DiscountService) request.getServletContext().getAttribute("discountService");

    Discount d = new Discount();

    d.setTitle(request.getParameter("title"));
    d.setContent(request.getParameter("content"));
    d.setPhoto(request.getParameter("photo"));

    // 작성자는 로그인 사용자이다.
    HttpServletRequest httpRequest = request;
    Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");
    d.setWriter(loginUser);

    try {
      discountService.add(d);
      response.sendRedirect("list");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}