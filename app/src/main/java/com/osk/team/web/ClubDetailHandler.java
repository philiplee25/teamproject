package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;
import com.osk.team.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ClubDetailHandler {

    ClubService clubService;
    MemberService memberService;

    public ClubDetailHandler(ClubService clubService, MemberService memberService) {
        this.clubService = clubService;
        this.memberService = memberService;
    }

    @RequestMapping("/club/detail")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        Member loginUser = (Member) request.getSession().getAttribute("loginUser");

        int no = Integer.parseInt(request.getParameter("no"));

        Club c = clubService.get(no);
        List<Member> clubM = clubService.getMembers(no);
        c.setNowTotal(clubM.size() + 1);//현재 참여 인원 저장

        request.setAttribute("club", c);
        request.setAttribute("members", memberService.list(null));
        request.setAttribute("clubMembers", clubM);
        request.setAttribute("size", c.getNowTotal());

        return "/jsp/club/detail.jsp";
    }
}
