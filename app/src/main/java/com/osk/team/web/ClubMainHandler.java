package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.service.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/club/main")
public class ClubMainHandler extends HttpServlet {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<p><a href='detailSearch'>참여</a></p>");
        out.print("<p><a href='club.html'>생성</a></p>");

        try {
            String arrive = request.getParameter("arrive");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String theme = request.getParameter("theme");

            List<Club> clubs = clubService.search(arrive, startDate, endDate, theme);

            out.println("<form method='get'>");
            out.println("<fieldset>");
            out.println("  <legend>클럽 상세 검색</legend>");
            out.println("  <table border='1'>");
            out.println("  <tbody>");

            out.printf("  <tr> <th>도착지</th>"
                            + " <td><input type='search' name='arrive' value='%s'></td> </tr>\n",
                    arrive != null ? arrive : "");

            out.printf("  <tr> <th>가는날</th>"
                            + " <td><input type='date' name='startDate' value='%s'></td> </tr>\n",
                    startDate != null ? startDate : "");

            out.printf("  <tr> <th>오는날</th>"
                            + " <td><input type='date' name='endDate' value='%s'></td> </tr>\n",
                    startDate != null ? startDate : "");

            out.printf("  <tr> <th>테마</th>"
                            + " <td><input type='search' name='theme' value='%s'></td> </tr>\n",
                    theme != null ? theme : "");

            out.println("  <tr> <td colspan='2'><button>검색</button></td> </tr>");
            out.println("  </tbody>");
            out.println("  </table>");
            out.println("</fieldset>");
            out.println("</form>");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
