package com.osk.team.web;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/member/update")
public class MemberUpdateHandler extends HttpServlet {

  private String uploadDir;

  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath("/upload");
  }
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

    try {
      request.setCharacterEncoding("UTF-8");
      int no = Integer.parseInt(request.getParameter("no"));

      Member oldMember = memberService.get(no);
      if (oldMember == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } 

      /*Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (oldMember.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("변경 권한이 없습니다!");
      }*/

      Member member = new Member();
      //m.setName(request.getParameter("name"));
      member.setNo(oldMember.getNo());
      member.setPassword(request.getParameter("password"));
      //m.setEmail(request.getParameter("email"));
      member.setTel(Integer.parseInt(request.getParameter("tel")));
      //m.setGender(Integer.parseInt(request.getParameter("gender")));
      //m.setBirth(Date.valueOf(request.getParameter("birth")));
      //m.setStatus(Integer.parseInt(request.getParameter("status")));
      //m.setPower(Integer.parseInt(request.getParameter("power")));
      //m.setStatus(Integer.parseInt(request.getParameter("count")));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        // 파일을 선택해서 업로드 했다면,
        String filename = UUID.randomUUID().toString();
        photoPart.write(this.uploadDir + "/" + filename);
        member.setPhoto(filename);

        // 썸네일 이미지 생성
        Thumbnails.of(this.uploadDir + "/" + filename)
        .size(30, 30)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_30x30";
          }
        });

        Thumbnails.of(this.uploadDir + "/" + filename)
        .size(80, 80)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_80x80";
          }
        });
      }

      memberService.update(member);
      response.sendRedirect("list");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}