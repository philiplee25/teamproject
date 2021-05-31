package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;
import com.osk.team.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ClubDeleteHandler {

    ClubService clubService;
    MemberService memberService;

    public ClubDeleteHandler(ClubService clubService, MemberService memberService) {
        this.clubService = clubService;
        this.memberService = memberService;
    }

    @RequestMapping("/club/delete")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int no = Integer.parseInt(request.getParameter("no"));

        Club oldClub = clubService.get(no);
        if (oldClub == null) {
            throw new Exception("해당 번호의 클럽이 없습니다.");
        }

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (oldClub.getWriter().getNo() != loginUser.getNo() && loginUser.getPower() == 0) {
            throw new Exception("삭제 권한이 없습니다.");
        }

//            운영자가 삭제를 하면 제제횟수+1 과 해당 글을 신고 처리 완료로 한다
            if (loginUser.getPower() == 1) {

                Club club = clubService.get(no);
                int count = club.getWriter().getCount() + 1;

                Member member = club.getWriter();
                member.setCount(count);

                memberService.update(member);
            }

        clubService.delete(no);
        return "redirect:list";
    }

    private void setCount(Member member) {
        if (member.getCount() == 0) {
            member.setCount(1);
        } else if (member.getCount() > 1) {
            member.setCount(member.getCount() + 1);
        }
    }
}
