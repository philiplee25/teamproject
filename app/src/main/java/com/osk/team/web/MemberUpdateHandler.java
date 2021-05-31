package com.osk.team.web;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class MemberUpdateHandler {

  MemberService memberService;

  public MemberUpdateHandler(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/update")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String uploadDir = request.getServletContext().getRealPath("/upload");
    int no = Integer.parseInt(request.getParameter("no"));

    Member oldMember = memberService.get(no);
    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }


    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (oldMember.getNo() != loginUser.getNo() && loginUser.getPower() == 0) {
      throw new Exception("변경 권한이 없습니다!");
    }

    //      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    //      if (oldMember.getWriter().getNo() != loginUser.getNo()) {
    //        throw new Exception("변경 권한이 없습니다!");
    //      }

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
      String filename = UUID.randomUUID().toString();
      photoPart.write(uploadDir + "/" + filename);
      member.setPhoto(filename);

      Thumbnails.of(uploadDir + "/" + filename)
              .size(30, 30)
              .outputFormat("jpg")
              .crop(Positions.CENTER)
              .toFiles(new Rename() {
                @Override
                public String apply(String name, ThumbnailParameter param) {
                  return name + "_30x30";
                }
              });

      Thumbnails.of(uploadDir + "/" + filename)
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

    return "redirect:list";

  }
}