package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
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
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/club/add")
public class ClubAddHandler extends HttpServlet {

    private String uploadDir;

    @Override
    public void init() throws ServletException {//사진 저장소
        this.uploadDir = this.getServletContext().getRealPath("/upload");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>클럽 등록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>클럽 등록</h1>");

        try {
            Club c = new Club();

            c.setArrive(request.getParameter("arrive"));
            c.setStartDate(Date.valueOf(request.getParameter("startDate")));
            c.setEndDate(Date.valueOf(request.getParameter("endDate")));
            c.setTheme(request.getParameter("theme"));
            c.setTitle(request.getParameter("title"));
            c.setContent(request.getParameter("content"));
            c.setTotal(Integer.parseInt(request.getParameter("count")));

            //방장이니까 한명이 클럽생성하면 자동 증가코드 추가하기

            Member loginUser = (Member) request.getSession().getAttribute("loginUser");//회원번호로 받기
            c.setWriter(loginUser);

//            String[] members = request.getParameterValues("members");//클럽 생성자가 들어간다
//            ArrayList<Member> memberList = new ArrayList<>();
//            for (String value : members) {
//                Member m = new Member();
//                m.setNo(Integer.parseInt(value));
//                memberList.add(loginUser);
//            }
//            c.setMembers(memberList);

            ///////////////사진 받기
            List<String> photos = new ArrayList<>();
            Part photoPart = request.getPart("photo1");
                if (photoPart.getSize() > 0) {

                    // 파일을 선택해서 업로드 했다면,
                    String filename = UUID.randomUUID().toString();
                    photoPart.write(this.uploadDir + "/" + filename);
                    photos.add(filename);

                    // 썸네일 이미지 생성
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

            clubService.add(c);
            out.println("<p>클럽 등록했습니다.</p>");
            response.setHeader("Refresh", "0;url=list");

        } catch (Exception e) {
            StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter);
            e.printStackTrace(printWriter);

            out.printf("<pre>%s</pre>\n", strWriter.toString());
            out.println("<p><a href='list'>클럽 목록</a></p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
