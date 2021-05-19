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
import java.io.StringWriter;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/club/list")
public class ClubListHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");

        try {

            List<Club> clubs = null;

            String arrive = request.getParameter("arrive");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String theme = request.getParameter("theme");

            if ((arrive != null && arrive.length() > 0) ||
                    (startDate != null && startDate.length() > 0) ||
                    (endDate != null && endDate.length() > 0) ||
                    (theme != null && theme.length() > 0)) {
                clubs = clubService.search(arrive, startDate, endDate, theme);
            } else {
                clubs = clubService.list();
            }

            request.setAttribute("clubs", clubs);
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/jsp/club/list.jsp").include(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
