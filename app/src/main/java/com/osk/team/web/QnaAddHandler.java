package com.osk.team.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Member;
import com.osk.team.domain.Qna;
import com.osk.team.service.QnaService;

@Controller
public class QnaAddHandler {

  QnaService qnaService;

  public QnaAddHandler(QnaService qnaService) {
    this.qnaService = qnaService;
  }

  @RequestMapping("/qna/add")
  public String execute(HttpServletRequest request, HttpServletResponse response)
          throws Exception {

    if (request.getMethod().equals("GET")) {
      return "/jsp/qna/form.jsp";
    }

    Qna q = new Qna();

    q.setTitle(request.getParameter("title"));
    q.setContent(request.getParameter("content"));

    // 작성자는 로그인 사용자이다.
    HttpServletRequest httpRequest = request;
    Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");
    q.setWriter(loginUser);

    qnaService.add(q);
    return "redirect:list";
  }
}



