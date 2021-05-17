package com.osk.team.web;

import java.io.IOException;
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

    int no = Integer.parseInt(request.getParameter("no"));

    try {
      Discount discount = discountService.get(no);

      request.setAttribute("discount", discount);

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/jsp/discount/detail.jsp").include(request, response);


    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}