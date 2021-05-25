package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
@WebServlet("/club/join")
public class ClubJoinHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");

        try {
            int no = Integer.parseInt(request.getParameter("no"));

            Member loginUser = (Member) request.getSession().getAttribute("loginUser");
            Club club = new Club();

            System.out.println(no);
            System.out.println(loginUser.getNo());

            HashMap<String, Object> params = new HashMap<>();
            params.put("memberNo", loginUser.getNo());
            params.put("clubNo", no);

            clubService.addWithMember(params);
            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
