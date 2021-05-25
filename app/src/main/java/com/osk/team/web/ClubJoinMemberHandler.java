package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;
import com.osk.team.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("serial")
@WebServlet("/club/member")
public class ClubJoinMemberHandler extends HttpServlet {
    //수정 코너에서 현재 인원들 탈퇴 기능 넣기

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");
        MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

        Club club = new Club();

        try {
            int no = Integer.parseInt(request.getParameter("no"));

            System.out.println(no);

            request.setAttribute("clubNo", club.getNo());
            request.setAttribute("member", memberService);
            request.setAttribute("clubMembers", clubService.getMembers(no));

            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
