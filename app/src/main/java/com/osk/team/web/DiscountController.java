package com.osk.team.web;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Discount;
import com.osk.team.domain.Member;
import com.osk.team.service.DiscountService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("/discount/")
public class DiscountController {

  DiscountService discountService;

  public DiscountController(DiscountService discountService) {
    this.discountService = discountService;
  }

  @RequestMapping("add")
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

      // 썸네일 이미지 생성
      Thumbnails.of(uploadDir + "/" + filename).size(200, 200).outputFormat("jpg")
      .crop(Positions.CENTER).toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_200x200";
        }
      });
    }
    discountService.add(d);
    return "redirect:list";
  }

  @RequestMapping("delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Discount oldDiscount = discountService.get(no);
    if (oldDiscount== null) {
      throw new Exception("해당 번호의 할인정보가 없습니다.");
    }

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (1 != loginUser.getPower()) {
      throw new Exception("삭제 권한이 없습니다!");
    }
    discountService.delete(no);
    return "redirect:list";
  }

  @RequestMapping("detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Discount d = discountService.get(no);
    request.setAttribute("discount", d);
    return "/jsp/discount/detail.jsp";
  }

  @RequestMapping("list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String keyword = request.getParameter("keyword");
    List<Discount> discounts = null;
    if (keyword != null && keyword.length() > 0) {
      discounts = discountService.search(keyword);
    } else {
      discounts = discountService.list();
    }
    request.setAttribute("list", discounts);
    return "/jsp/discount/list.jsp";
  }

  @RequestMapping("update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

      Thumbnails.of(uploadDir + "/" + filename)
      .size(200, 200)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_200x200";
        }
      });
    }
    discountService.update(d);
    return "redirect:list";
  }
}