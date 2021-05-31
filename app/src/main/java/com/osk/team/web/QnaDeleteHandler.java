package com.osk.team.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Member;
import com.osk.team.domain.Qna;
import com.osk.team.service.QnaService;

@Controller
public class QnaDeleteHandler {

  QnaService qnaService;

  public QnaDeleteHandler(QnaService qnaService) {
    this.qnaService = qnaService;
  }

  @RequestMapping("/qna/delete")
  public String execute(HttpServletRequest request, HttpServletResponse response)
          throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Qna oldQna = qnaService.get(no);
    if (oldQna == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (oldQna.getWriter().getNo() != loginUser.getNo()) {
      throw new Exception("삭제 권한이 없습니다!");
    }

    qnaService.delete(no);

    return "redirect:list";
  }
}