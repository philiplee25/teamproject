package com.osk.team.web;

import com.osk.team.domain.Discount;
import com.osk.team.domain.Member;
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
public class DiscountUpdateHandler {

  DiscountService discountService;

  public DiscountUpdateHandler(DiscountService discountService) {
    this.discountService = discountService;
  }

  @RequestMapping("/discount/update")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      String uploadDir = request.getServletContext().getRealPath("/upload");
      int no = Integer.parseInt(request.getParameter("no"));

      Discount oldDiscount = discountService.get(no);
      if (oldDiscount== null) {
        throw new Exception("해당 번호의 할인정보가 없습니다.");
      }

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (1 != loginUser.getPower()) {
        throw new Exception("변경 권한이 없습니다!");
      }

      Discount d = new Discount();
      d.setNo(oldDiscount.getNo());
      d.setTitle(request.getParameter("title"));
      d.setContent(request.getParameter("content"));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoPart.write(uploadDir + "/" + filename);
        d.setPhoto(filename);

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

      discountService.update(d);
      return "redirect:list";

    }
  }