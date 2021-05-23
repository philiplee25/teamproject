package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.domain.Photo;
import com.osk.team.service.ClubService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/club/add")
public class ClubAddHandler extends HttpServlet {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private String uploadDir;
    private List<Part> partList;//사진 리스트
    private List<Photo> photos;//사진 정보 리스트

    @Override
    public void init() throws ServletException {//사진 저장소
        this.uploadDir = this.getServletContext().getRealPath("/upload");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/jsp/club/form.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");
        partList = new ArrayList<Part>();
        photos = new ArrayList<Photo>();
        Club c = new Club();

        c.setArrive(request.getParameter("arrive"));
        c.setStartDate(Date.valueOf(request.getParameter("startDate")));
        c.setEndDate(Date.valueOf(request.getParameter("endDate")));
        c.setTheme(request.getParameter("theme"));
        c.setTitle(request.getParameter("title"));
        c.setContent(request.getParameter("content"));
        c.setTotal(Integer.parseInt(request.getParameter("count")));

            //방장이니까 한명이 클럽생성하면 자동 증가코드 추가하기

//            String[] members = request.getParameterValues("members");//클럽 생성자가 들어간다
//            ArrayList<Member> memberList = new ArrayList<>();
//            for (String value : members) {
//                Member m = new Member();
//                m.setNo(Integer.parseInt(value));
//                memberList.add(loginUser);
//            }
//            c.setMembers(memberList);

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
                partList.get(i).write(this.uploadDir + "/" + filename);
                photos.get(i).setName(filename);

                Thumbnails.of(this.uploadDir + "/" + filename)
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

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");//회원번호로 받기
        c.setWriter(loginUser);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>클럽 등록</title>");

        try {

            clubService.add(c);
            int p_cno = clubService.getClubCno().getNo();//같은 p_cno 번호 셋팅
            for (int i = 0; i < photos.size(); i++) {//반복문을 돌려서 갯수만큼 db에 저장
                photos.get(i).setNo(p_cno);//등록된 게시글의 cno값을 출력해서 넣어줌 사짐cno값 넣어줌
                clubService.addWithPhoto(photos.get(i));//클럽 사진 등록 /사진 cno값과 이름값 함께 insert 해준다
            }
            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
