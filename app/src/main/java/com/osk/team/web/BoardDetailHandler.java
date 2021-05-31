package com.osk.team.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osk.team.domain.Board;
import com.osk.team.service.BoardService;



@Controller
public class BoardDetailHandler {

  BoardService  boardService;

  public BoardDetailHandler(BoardService  boardService) {
    this.boardService = boardService;
    System.out.println("boardDetailHander 빈객체 생성");
  }

  @RequestMapping("board/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    int no = Integer.parseInt(request.getParameter("no"));

    Board board = boardService.get(no);
    //      System.out.println("2222222222");
    //      System.out.println("사진크기 확인 "+board.getPhotos().size());
    request.setAttribute("board", board);

    //      request.getRequestDispatcher("/jsp/board/detail.jsp").include(request, response); //jsp때문에추가


    return "/jsp/board/detail.jsp";
  }
}





