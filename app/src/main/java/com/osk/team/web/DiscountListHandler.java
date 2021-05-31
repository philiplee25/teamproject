package com.osk.team.web;

import com.osk.team.domain.Discount;
import com.osk.team.service.DiscountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DiscountListHandler {

  DiscountService discountService;

  public DiscountListHandler(DiscountService discountService) {
    this.discountService = discountService;
  }

  @RequestMapping("/discount/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      String keyword = request.getParameter("keyword");
      List<Discount> discounts = null;
      if (keyword != null && keyword.length() > 0) {
        discounts = discountService.search(keyword);
      } else {
        discounts = discountService.list();
      }

      request.setAttribute("list", discounts);


      return "/jsp/discount/list.jsp";

  }
}