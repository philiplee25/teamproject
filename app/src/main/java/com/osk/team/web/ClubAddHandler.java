package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.domain.Photo;
import com.osk.team.service.ClubService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ClubAddHandler {

    ClubService clubService;

    public ClubAddHandler(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/club/add")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uploadDir = request.getServletContext().getRealPath("/upload");

        if (request.getMethod().equals("GET")) {
            return "/jsp/club/form.jsp";
        }

        List<Part> partList = new ArrayList<Part>();
        List<Photo> photos = new ArrayList<Photo>();

        Club c = new Club();
        c.setArrive(request.getParameter("arrive"));
        c.setStartDate(Date.valueOf(request.getParameter("startDate")));
        c.setEndDate(Date.valueOf(request.getParameter("endDate")));
        c.setTheme(request.getParameter("theme"));
        c.setTitle(request.getParameter("title"));
        c.setContent(request.getParameter("content"));
        c.setTotal(Integer.parseInt(request.getParameter("count")));

        if (request.getPart("photo1").getSize() > 0) {
            partList.add(request.getPart("photo1"));
        }
        if (request.getPart("photo2").getSize() > 0) {
            partList.add(request.getPart("photo2"));
        }
        if (request.getPart("photo3").getSize() > 0) {
            partList.add(request.getPart("photo3"));
        }

        for (int i = 0; i < partList.size(); i++) {
            if (partList.get(i).getSize() > 0) {
                photos.add(new Photo());

                String filename = UUID.randomUUID().toString();
                partList.get(i).write(uploadDir + "/" + filename);
                photos.get(i).setName(filename);

                Thumbnails.of(uploadDir + "/" + filename)
                        .size(254, 178)
                        .outputFormat("jpg")
                        .crop(Positions.CENTER)
                        .toFiles(new Rename() {
                            @Override
                            public String apply(String name, ThumbnailParameter param) {
                                return name + "_254x178";
                            }
                        });
            }
        }
        HttpServletRequest httpRequest = request;
        Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");//회원번호로 받기
        c.setWriter(loginUser);

        clubService.add(c);
        int p_cno = clubService.getClubCno().getNo();//같은 p_cno 번호 셋팅
        for (int i = 0; i < photos.size(); i++) {//반복문을 돌려서 갯수만큼 db에 저장

            photos.get(i).setNo(p_cno);//등록된 게시글의 cno값을 출력해서 넣어줌 사짐cno값 넣어줌
            clubService.addWithPhoto(photos.get(i));//클럽 사진 등록 /사진 cno값과 이름값 함께 insert 해준다
        }
        return "redirect:list";
    }
}
