package com.osk.team.web;

import com.osk.team.domain.Discount;
import com.osk.team.domain.Member;
import com.osk.team.service.DiscountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DiscountDeleteHandler {

  DiscountService discountService;

  public DiscountDeleteHandler(DiscountService discountService) {
    this.discountService = discountService;
  }

  @RequestMapping("/discount/delete")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      int no = Integer.parseInt(request.getParameter("no"));

      Discount oldDiscount = discountService.get(no);
      if (oldDiscount== null) {
        throw new Exception("해당 번호의 할인정보가 없습니다.");
      }

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (1 != loginUser.getPower()) {
        throw new Exception("삭제 권한이 없습니다!");
      }

      discountService.delete(no);
      return "redirect:list";
  }
}