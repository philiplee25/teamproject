package com.osk.team.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osk.team.domain.Faq;
import com.osk.team.domain.Member;
import com.osk.team.service.FaqService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FaqDeleteHandler {

  FaqService faqService;

  public FaqDeleteHandler(FaqService faqService) {
    this.faqService = faqService;
  }

  @RequestMapping("/faq/delete")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Faq oldFaq = faqService.get(no);
    if (oldFaq == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (1 != loginUser.getPower()) {
        throw new Exception("삭제 권한이 없습니다!");
      }

      faqService.delete(no);
      return "redirect:list";

    }
  }
