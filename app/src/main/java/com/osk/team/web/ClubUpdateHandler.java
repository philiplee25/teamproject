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

@SuppressWarnings("serial")
@WebServlet("/club/update")
public class ClubUpdateHandler extends HttpServlet {
    //수정 코너에서 현재 인원들 탈퇴 기능 넣기

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/jsp/club/form.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");

        try {
            int no = Integer.parseInt(request.getParameter("no"));

            System.out.println(no);

            Club oldClub = clubService.get(no);

            if (oldClub == null) {
                throw new Exception("해당 번호의 클럽이 없습니다.");
            }

            Member loginUser = (Member) request.getSession().getAttribute("loginUser");
            if (oldClub.getWriter().getNo() != loginUser.getNo()) {
                throw new Exception("변경 권한이 없습니다!");
            }

            // 사용자에게서 변경할 데이터를 입력 받는다.
            Club c = new Club();
            c.setNo(oldClub.getNo());
            c.setTitle(request.getParameter("title"));
            c.setContent(request.getParameter("content"));

            // ...&member=1&member=18&member=23
            String[] values = request.getParameterValues("member");
            ArrayList<Member> memberList = new ArrayList<>();
            if (values != null) {
                for (String value : values) {
                    Member member = new Member();
                    member.setNo(Integer.parseInt(value));
                    memberList.add(member);
                }
            }
            c.setMembers(memberList);

            //클럽 참여 기능
//            ArrayList<Member> joinMember = new ArrayList<>();
//            for (int i = 0; i < c.getTotal(); i++) {
//                //방장입장 불가 / 이미 참여한 멤버 입장 불가 / 로그인 안한 멤버 입장 불가
//                if (c.getWriter().getNo() != loginUser.getNo() || c.getMembers().get(i).getNo() != loginUser.getNo() || loginUser == null) {
//                    joinMember.add(loginUser);
//                }
//            }
//            if (c.getWriter().getNo() != loginUser.getNo()) {
//                joinMember.add(loginUser);
//            }

//            c.setMembers(joinMember);//멤버 참여

            clubService.update(c);

            response.sendRedirect("list");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
