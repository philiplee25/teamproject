package com.osk.team.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osk.team.domain.Board;
import com.osk.team.domain.Member;
import com.osk.team.service.BoardService;

@Controller
public class BoardDeleteHandler  {

  BoardService boardService;

  public BoardDeleteHandler(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("board/delete")
  public String execute(HttpServletRequest request, HttpServletResponse response)
          throws Exception {


    int no = Integer.parseInt(request.getParameter("no"));

    Board oldBoard = boardService.get(no);
    if (oldBoard == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");

    if (oldBoard.getWriter().getNo() != loginUser.getNo() && loginUser.getPower() == 0) {
      throw new Exception("삭제 권한이 없습니다!");
    }

    boardService.delete(no);

    response.sendRedirect("list");

    return "redirect:list";
  }
}





