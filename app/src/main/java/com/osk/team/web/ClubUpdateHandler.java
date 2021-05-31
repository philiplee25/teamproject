package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ClubUpdateHandler {

    ClubService clubService;

    public ClubUpdateHandler(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/club/update")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));

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

        clubService.update(c);

        return "redirect:list";
    }
}
