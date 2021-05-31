package com.osk.team.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Member;
import com.osk.team.domain.Qna;
import com.osk.team.service.QnaService;

@Controller("/qna/update")
public class QnaUpdateHandler {

  QnaService qnaService;

  public QnaUpdateHandler(QnaService qnaService) {
    this.qnaService = qnaService;
  }

  @RequestMapping("/qna/update")
  public String execute(HttpServletRequest request, HttpServletResponse response)
          throws Exception {

    QnaService qnaService = (QnaService) request.getServletContext().getAttribute("qnaService");

    int no = Integer.parseInt(request.getParameter("no"));

    Qna oldQna = qnaService.get(no);
    if (oldQna == null) {
      throw new Exception("해당 번호의 QnA가 없습니다.");
    }
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser.getPower() == 0 && oldQna.getWriter().getNo() != loginUser.getNo()) {
      throw new Exception("변경 권한이 없습니다!");
    }

    Qna qna = new Qna();
    qna.setNo(oldQna.getNo());
    qna.setTitle(request.getParameter("title"));
    qna.setContent(request.getParameter("content"));
    qna.setAnswer(request.getParameter("answer"));
    qnaService.update(qna);

    return "redirect:list";
  }
}