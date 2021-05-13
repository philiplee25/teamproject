package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osk.team.domain.Board;
import com.osk.team.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board/add")
public class BoardAddHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BoardService boardService = (BoardService) request.getServletContext().getAttribute("boardService");

        Board b = new Board();

        request.setCharacterEncoding("UTF-8");

//        b.setBtitle(request.getParameter("title"));
//        b.setBcontent(request.getParameter("content"));


        HttpServletRequest httpRequest = request;
        int loginUser = (int) httpRequest.getSession().getAttribute("loginUser");
//        b.setMember_no(loginUser);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>게시글 등록</title>");

        try {
            boardService.add(b);

            out.println("<meta http-equiv='Refresh' content='1;url=list'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>게시글 등록</h1>");
            out.println("<p>게시글을 등록했습니다.</p>");

        } catch (Exception e) {
            StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter);
            e.printStackTrace(printWriter);

            out.println("</head>");
            out.println("<body>");
            out.println("<h1>게시글 등록오류</h1>");
            out.printf("<pre>%s</pre>\n",strWriter.toString());
            out.println("<p><a href='list'>목록</a></p>");

        }

        out.println("</body>");
        out.println("</html>");
    }
}






