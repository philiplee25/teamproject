package com.osk.team.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Faq;
import com.osk.team.domain.Member;
import com.osk.team.service.FaqService;

@Controller
@RequestMapping("/faq/")
public class FaqController {

  FaqService faqService;

  public FaqController(FaqService faqService) {
    this.faqService = faqService;
  }

  @RequestMapping("add")
  public String add (HttpServletRequest request, HttpServletResponse response) throws Exception {

    if (request.getMethod().equals("GET")) {
      return "/jsp/faq/form.jsp";
    }

    Faq f = new Faq();
    f.setTitle(request.getParameter("title"));
    f.setContent(request.getParameter("content"));
    faqService.add(f);
    return "redirect:list";
  }

  @RequestMapping("delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

  @RequestMapping("detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    Faq faq = faqService.get(no);
    request.setAttribute("faq", faq);
    return "/jsp/faq/detail.jsp";
  }

  @RequestMapping("list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

  @RequestMapping("update")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Faq oldFaq = faqService.get(no);
    if (oldFaq== null) {
      throw new Exception("해당 번호의 FAQ가 없습니다.");
    }

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (1 != loginUser.getPower()) {
      throw new Exception("변경 권한이 없습니다!");
    }

    Faq f = new Faq();
    f.setNo(oldFaq.getNo());
    f.setTitle(request.getParameter("title"));
    f.setContent(request.getParameter("content"));
    faqService.update(f);
    return "redirect:list";

  }
}