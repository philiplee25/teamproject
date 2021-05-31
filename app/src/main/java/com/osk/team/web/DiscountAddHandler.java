package com.osk.team.web;

import com.osk.team.domain.Discount;
import com.osk.team.service.DiscountService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.UUID;

@Controller
public class DiscountAddHandler {

  DiscountService discountService;

  public DiscountAddHandler(DiscountService discountService) {
    this.discountService = discountService;
  }

  @RequestMapping("/discount/add")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String uploadDir = request.getServletContext().getRealPath("/upload");

    if (request.getMethod().equals("GET")) {
      return "/jsp/discount/form.jsp";
    }

    Discount d = new Discount();
    d.setTitle(request.getParameter("title"));
    d.setContent(request.getParameter("content"));
    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      // 파일을 선택해서 업로드 했다면,
      String filename = UUID.randomUUID().toString();
      photoPart.write(uploadDir + "/" + filename);
      d.setPhoto(filename);

      // 썸네일 이미지 생성
      Thumbnails.of(uploadDir + "/" + filename).size(80, 80).outputFormat("jpg")
              .crop(Positions.CENTER).toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });
    }

      discountService.add(d);
      return "redirect:list";

  }
}