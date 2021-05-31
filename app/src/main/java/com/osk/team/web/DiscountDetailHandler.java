package com.osk.team.web;

import com.osk.team.domain.Discount;
import com.osk.team.service.DiscountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DiscountDetailHandler {

  DiscountService discountService;

  public DiscountDetailHandler(DiscountService discountService) {
    this.discountService = discountService;
  }

  @RequestMapping("discount/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

      Discount discount = discountService.get(no);

      request.setAttribute("discount", discount);

      return "/jsp/discount/detail.jsp";
  }
}