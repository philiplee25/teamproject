package com.osk.team.web;

import com.osk.team.domain.Faq;
import com.osk.team.service.FaqService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FaqAddHandler {

  FaqService faqService;

  public FaqAddHandler(FaqService faqService) {
  this.faqService = faqService;
  }

  @RequestMapping("/faq/add")
  public String execute (HttpServletRequest request, HttpServletResponse response) throws Exception {

    if (request.getMethod().equals("GET")) {
      return "/jsp/faq/form.jsp";
    }

      Faq f = new Faq();

      f.setTitle(request.getParameter("title"));
      f.setContent(request.getParameter("content"));

      faqService.add(f);
      return "redirect:list";

    }
  }