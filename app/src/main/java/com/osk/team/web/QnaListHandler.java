package com.osk.team.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Qna;
import com.osk.team.service.QnaService;

@Controller
public class QnaListHandler {

  QnaService qnaService;

  public QnaListHandler(QnaService qnaService) {
    this.qnaService = qnaService;
    System.out.println("qnaService 객체 생성됨!");
  }

  @RequestMapping("/qna/list")
  public String execute(HttpServletRequest request, HttpServletResponse response)
          throws Exception {

    String myContent = request.getParameter("keyword");
    List<Qna> qnas = null;
    if (myContent != null) {
      qnas = qnaService.search(myContent);
    } else {
      qnas = qnaService.listAll();
    }

    request.setAttribute("list", qnas);

    return "/jsp/qna/list.jsp";
  }
}