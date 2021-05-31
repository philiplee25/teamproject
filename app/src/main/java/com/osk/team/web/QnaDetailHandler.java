package com.osk.team.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Qna;
import com.osk.team.service.QnaService;

@Controller
public class QnaDetailHandler {

  QnaService qnaService;

  public QnaDetailHandler(QnaService qnaService) {
    this.qnaService = qnaService;
  }

  @RequestMapping("/qna/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response)
          throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    System.out.println(no);

    Qna qna = qnaService.get(no);
    request.setAttribute("qna", qna);
    return "/jsp/qna/detail.jsp";
  }
}
