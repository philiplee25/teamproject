package com.osk.team.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osk.team.domain.Board;
import com.osk.team.service.BoardService;

@Controller
public class BoardListHandler {

  BoardService boardService;

  public BoardListHandler(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/list")
  public String execute(HttpServletRequest request, HttpServletResponse response)
          throws Exception {



    // JSP가 게시글 목록을 출력할 때 사용할 데이터를 준비한다.
    String keyword = request.getParameter("keyword");
    List<Board> boards = null;
    if (keyword != null && keyword.length() > 0) {
      boards = boardService.search(keyword);
    } else {
      boards = boardService.list();
    }
    //      System.out.println("11111"); // 출력 테스트
    // JSP가 사용할 수 있도록 ServletRequest 보관소에 저장한다.
    request.setAttribute("list", boards);

    //request.getRequestDispatcher("/jsp/board/list.jsp").include(request, response);

    return "/jsp/board/list.jsp";
  }
}





