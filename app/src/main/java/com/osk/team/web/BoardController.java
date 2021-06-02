
package com.osk.team.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osk.team.domain.Board;
import com.osk.team.domain.BoardPhoto;
import com.osk.team.domain.Member;
import com.osk.team.service.BoardService;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

//@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@Controller
public class BoardController {

  BoardService boardService;

  private List<Part> partList = new ArrayList<>();          // 사진 리스트  
  private List<BoardPhoto> Photos = new ArrayList<>();       // 사진 정보 리스트

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡAdd
  @RequestMapping("/board/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String uploadDir = request.getServletContext().getRealPath("/upload");

    if (request.getMethod().equals("GET")) {
      return "/jsp/board/form.jsp";
    }
    Board b = new Board();

    b.setTitle(request.getParameter("title"));
    b.setContent(request.getParameter("content"));
    b.setViewCount(1);

    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); // 날짜 포멧 형식 지정 및 생성
    //    System.out.println(sdf.format(d)); // 위에서 지정한 포멧 형식으로 날짜 출력
    b.setRegisteredDate(sdf.format(d));

    if(!"".equals(request.getParameter("boardtype"))) {
      b.setBoardTypeNo(Integer.parseInt(request.getParameter("boardtype")));
    }
    else {
      b.setBoardTypeNo(1);      
    }

    ///-----사진처리
    if(request.getPart("photo1").getSize()>0){
      partList.add(request.getPart("photo1")); // 사진값을 리스트에 순서대로 담아둠.
      //      System.out.println("1111111111111"+request.getPart("photo1")); //출력테스트
    }
    if(request.getPart("photo2").getSize()>0){
      partList.add(request.getPart("photo2"));
    }
    if(request.getPart("photo3").getSize()>0){
      partList.add(request.getPart("photo3"));
    }

    for(int i=0; i<partList.size(); i++) { // 리스트 수만큼 반복문 돌림
      if (partList.get(i).getSize() > 0) { // 해당 번째에 사이즈 있느냐 없느냐로, 사진이 들어갔나 체크
        Photos.add(new BoardPhoto()); // 사진 정보를 생성해서 사진 정보 리스트에 순서대로 넣음
        // 파일을 선택해서 업로드 했다면,
        String filename = UUID.randomUUID().toString();
        partList.get(i).write(uploadDir + "/" + filename);
        Photos.get(i).setPhoto(filename); //만들어진 사진 정보를 get해서 그 값을 set으로 바로 넣어줌

        // 썸네일 이미지 생성
        Thumbnails.of(uploadDir + "/" + filename)
        .size(250, 250)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_250x250";
          }
        });
      }
      //사진처리----
    }

    HttpServletRequest httpRequest = request;
    Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");
    b.setWriter(loginUser);

    boardService.add(b);// 게시판 생성
    //        System.out.println("bno확인" + boardService.getBoardBno()); //1. 게시글을 등록하고
    int p_bno=boardService.getBoardBno().getNo(); // 같은 p_bno 번호 셋팅
    for(int i=0; i<Photos.size(); i++) { // 반복문 돌려서 갯수만큼 db에 저장.
      Photos.get(i).setBno(p_bno); //2. 등록된 게시글의  bno값을 출력해서 넣어줌 사진bno값 넣어줌
      boardService.addWithPhoto(Photos.get(i));// 게시판 사진 등록 3. 사진bno값과 이름값 함께 insert 해줌
    }
    return "redirect:list";
  }

  //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡList
  @RequestMapping("board/list")
  public String list(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    // JSP가 게시글 목록을 출력할 때 사용할 데이터를 준비한다.
    /*tring keyword = request.getParameter("keyword");
    List<Board> boards = null;
    if (keyword != null && keyword.length() > 0) {
      boards = boardService.search(keyword);
    } else {
      boards = boardService.list();
    }
    //      System.out.println("11111"); // 출력 테스트
    // JSP가 사용할 수 있도록 ServletRequest 보관소에 저장한다.
    request.setAttribute("list", boards);*/

    HashMap<String,Object> map = new HashMap<>();
    map.put("boardtype",request.getParameter("boardtype"));
    map.put("keyword",request.getParameter("keyword"));

    System.out.println(map.get("boardtype") +" "+map.get("keyword"));

    List<Board> boards = null;
    boards = boardService.list(map);
    request.setAttribute("list", boards);
    request.setAttribute("boardtype", request.getParameter("boardtype"));

    // 목록 출력을 JSP에게 맡긴다.
    response.setContentType("text/html;charset=UTF-8");


    return "/jsp/board/list.jsp";
  }

  //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡBoardDetail
  @RequestMapping("board/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Board board = boardService.get(no);
    //      System.out.println("사진크기 확인 "+board.getPhotos().size());
    request.setAttribute("board", board);
    //      request.getRequestDispatcher("/jsp/board/detail.jsp").include(request, response); //jsp때문에추가

    return "/jsp/board/detail.jsp";
  }
  //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡUpdate
  @RequestMapping("board/update")
  public String update(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Board oldBoard = boardService.get(no);
    if (oldBoard == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (oldBoard.getWriter().getNo() != loginUser.getNo() && loginUser.getPower() == 0) {
      throw new Exception("변경 권한이 없습니다!");
    }

    Board board = new Board();
    board.setNo(oldBoard.getNo());
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));
    boardService.update(board);

    return "redirect:list";
  }
  //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡBoardDelete
  @RequestMapping("board/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response)
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

    //    response.sendRedirect("list");

    return "redirect:list";
  }

}





