package com.osk.team.web;

import com.osk.team.domain.Photo;
import com.osk.team.service.PhotoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// 이 서블릿은 프로젝트의 멤버를 선택하거나 현재 프로젝트 멤버를 출력하는 일을 한다.
// 직접 사용되지는 않고, 다른 서블릿의 요청을 보조하는 역할을 한다.
// 즉 인클루딩 목적으로 만든 서블릿이다.
//
@SuppressWarnings("serial")
@WebServlet("/club/photo/list")
public class ClubPhotoListHandler extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    PhotoService photoService = (PhotoService) request.getServletContext().getAttribute("photoService");

    // 인클루딩에서 사용되는 서블릿은 출력 콘텐트 타입을 설정할 필요가 없다.
    // 설정해도 소용이 없다.
    // 왜? 이 서블릿을 인클루딩하는 서블릿 쪽에서 설정하기 때문이다.
    // 
    // response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();

    try {
      List<Photo> photos = photoService.list(null);

      @SuppressWarnings("unchecked")
      List<Photo> clubPhotos = (List<Photo>) request.getAttribute("photos");

      for (Photo p : photos) {
        if (clubPhotos != null) {
          out.printf("  <input type='checkbox' name='photo1' value='%d' %s>%s<br>\n",
                  p.getNo(), contain(clubPhotos, p.getNo()) ? "checked" : "", p.getName());
        } else {
          out.printf("  <input type='checkbox' name='photo1' value='%d'>%s<br>\n", p.getNo(), p.getName());
        }
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  private boolean contain(List<Photo> photos, int clubNo) {
    for (Photo p : photos) {
      if (p.getNo() == clubNo) {
        return true;
      }
    }
    return false;
  }
}








