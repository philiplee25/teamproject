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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaqDetailHandler {

  FaqService faqService;

  public FaqDetailHandler(FaqService faqService) {
    this.faqService = faqService;
  }

  @RequestMapping("/faq/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response)       throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
      Faq faq = faqService.get(no);
      request.setAttribute("faq", faq);
      return "/jsp/faq/detail.jsp";
  }
}