package com.osk.team.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osk.team.domain.Faq;
import com.osk.team.service.FaqService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaqListHandler {

  FaqService faqService;

  public FaqListHandler(FaqService faqService) {
    this.faqService = faqService;
  }

  @RequestMapping("/faq/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      String keyword = request.getParameter("keyword");
      List<Faq> faqs = null;
      if (keyword != null && keyword.length() > 0) {
        faqs = faqService.search(keyword);
      } else {
        faqs = faqService.list();
      }
      request.setAttribute("list", faqs);
      return "/jsp/faq/list.jsp";

  }
}