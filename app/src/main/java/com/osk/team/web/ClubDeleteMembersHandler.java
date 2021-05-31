package com.osk.team.web;

import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ClubDeleteMembersHandler {

    ClubService clubService;

    public ClubDeleteMembersHandler(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/club/deleteMembers")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

//        Member loginUser = (Member) request.getSession().getAttribute("loginUser");

        int no = Integer.parseInt(request.getParameter("no"));
        List<Member> clubM = clubService.getMembers(no);

        clubService.deleteMember(no);
        request.setAttribute("clubMembers", clubM);
        return "redirect:list";

    }
}
