package com.osk.team.web;

import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class ClubJoinHandler {

    ClubService clubService;

    public ClubJoinHandler(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/club/join")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int no = Integer.parseInt(request.getParameter("no"));

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");

        HashMap<String, Object> params = new HashMap<>();
        params.put("memberNo", loginUser.getNo());
        params.put("clubNo", no);

        clubService.addWithMember(params);
        return "redirect:list";
    }
}
