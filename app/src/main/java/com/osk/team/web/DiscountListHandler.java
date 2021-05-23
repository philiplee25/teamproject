package com.osk.team.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osk.team.domain.Discount;
import com.osk.team.service.DiscountService;

@SuppressWarnings("serial")
@WebServlet("/discount/list")
public class DiscountListHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    DiscountService discountService = (DiscountService) request.getServletContext().getAttribute("discountService");

    // JSP가 게시글 목록을 출력할 때 사용할 데이터를 준비한다.  
    try {
      String keyword = request.getParameter("keyword");
      List<Discount> discounts = null;
      if (keyword != null && keyword.length() > 0) {
        discounts = discountService.search(keyword);
      } else {
        discounts = discountService.list();
      }

      // JSP가 사용할 수 있도록 ServletRequest 보관소에 저장한다.
      request.setAttribute("list", discounts);

      // 목록 출력을 JSP에게 맡긴다.
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/jsp/discount/list.jsp").include(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}