package com.osk.team.web;


import com.osk.team.domain.Club;
import com.osk.team.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ClubListHandler {

    ClubService clubService;

    public ClubListHandler(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/club/list")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        return "/jsp/club/list.jsp";
    }
}
