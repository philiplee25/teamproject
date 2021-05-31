package com.osk.team.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.osk.team.domain.Hotplace;
import com.osk.team.service.HotplaceService;

@Controller 
public class HotplaceListHandler {

  HotplaceService hotplaceService;

  public HotplaceListHandler(HotplaceService hotplaceService) {
    this.hotplaceService = hotplaceService;
  }

  @RequestMapping("/hotplace/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Hotplace> hotplaces = null;
    hotplaces = hotplaceService.list();

    request.setAttribute("list", hotplaces);

    return "/jsp/hotplace/list.jsp";

  }
}