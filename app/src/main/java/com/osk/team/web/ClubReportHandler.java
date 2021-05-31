package com.osk.team.web;

import com.osk.team.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class ClubReportHandler {

    ClubService clubService;

    public ClubReportHandler(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/club/report")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int no = Integer.parseInt(request.getParameter("no"));
        int clubWriterNo = Integer.parseInt(request.getParameter("clubWriterNo"));
        String reason = request.getParameter("reason");//신고 사유
        int result = Integer.parseInt(request.getParameter("result"));

//            Club club = new Club();

        HashMap<String, Object> params = new HashMap<>();
        params.put("memberNo", clubWriterNo);//클럽글 작성자 번호
        params.put("clubNo", no);
        params.put("reason", reason);
        params.put("result", result);
            //신고처리는 디폴트 맴퍼에서 0처리

        clubService.addWithReport(params);//신고하는 글과 작성자 번호 보내기

        return "redirect:list";
    }
}
