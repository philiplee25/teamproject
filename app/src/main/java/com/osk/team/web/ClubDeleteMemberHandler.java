package com.osk.team.web;

import com.osk.team.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ClubDeleteMemberHandler {

    ClubService clubService;

    public ClubDeleteMemberHandler(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/club/deleteMember")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        int no = Integer.parseInt(request.getParameter("no"));

        clubService.deleteMember(no);
        return "redirect:list";
    }
}
