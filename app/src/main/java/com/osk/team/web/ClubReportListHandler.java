package com.osk.team.web;


import com.osk.team.domain.Club;
import com.osk.team.service.ClubService;
import com.osk.team.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ClubReportListHandler {

    ClubService clubService;
    MemberService memberService;

    public ClubReportListHandler(ClubService clubService, MemberService memberService) {
        this.clubService = clubService;
        this.memberService = memberService;
    }

    @RequestMapping("/club/reportList")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Club> clubs = null;
        clubs = clubService.getReports();

        request.setAttribute("clubs", clubs);
        request.setAttribute("members", memberService.list(null));

        return "/jsp/club/reportList.jsp";
    }
}
