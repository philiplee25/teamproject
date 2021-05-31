package com.osk.team.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
public class MemberAddHandler {

  MemberService memberService;

  public MemberAddHandler(MemberService memberService) {
    this.memberService = memberService;
  }

  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");



  @RequestMapping("/member/addd")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String uploadDir = request.getServletContext().getRealPath("/upload");

    if (request.getMethod().equals("GET")) {
      return "/jsp/member/join.jsp";
    }

    Member m = new Member();
    //m.setNo(Integer.parseInt(request.getParameter("no")));
    m.setName(request.getParameter("name"));
    m.setPassword(request.getParameter("password"));
    m.setEmail(request.getParameter("email"));
    m.setTel(Integer.parseInt(request.getParameter("tel")));
    m.setGender(Integer.parseInt(request.getParameter("gender")));
    m.setBirth(Date.valueOf(request.getParameter("birth")));
    m.setStatus(0);
    m.setPower(0);
    m.setStatus(0);

    Part photoPart = request.getPart("photo");

    if (photoPart.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoPart.write(uploadDir + "/" + filename);
      m.setPhoto(filename);

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
    memberService.add(m);
    return "redirect:list";

  }
}
