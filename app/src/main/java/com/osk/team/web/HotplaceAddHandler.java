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
import com.osk.team.domain.Hotplace;
import com.osk.team.service.HotplaceService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024)
@WebServlet("/hotplace/add")
public class HotplaceAddHandler extends HttpServlet {

  private String uploadDir;

  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/jsp/hotplace/form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HotplaceService hotplaceService =
        (HotplaceService) request.getServletContext().getAttribute("hotplaceService");

    try {

      Hotplace h = new Hotplace();

      h.setTitle(request.getParameter("title"));
      h.setContent(request.getParameter("content"));
      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        // 파일을 선택해서 업로드 했다면,
        String filename = UUID.randomUUID().toString();
        photoPart.write(this.uploadDir + "/" + filename);
        h.setPhoto(filename);

        // 썸네일 이미지 생성
        Thumbnails.of(this.uploadDir + "/" + filename).size(80, 80).outputFormat("jpg")
            .crop(Positions.CENTER).toFiles(new Rename() {
              @Override
              public String apply(String name, ThumbnailParameter param) {
                return name + "_80x80";
              }
            });
      }
      h.setLatitude(request.getParameter("latitude"));
      h.setLongitude(request.getParameter("longitude"));

      hotplaceService.add(h);
      response.sendRedirect("list");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}