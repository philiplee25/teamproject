package com.osk.team.service;

import java.util.List;
import com.osk.team.domain.Hotplace;

public interface HotplaceService {

  int add(Hotplace hotplace) throws Exception;

  List<Hotplace> list() throws Exception;

  Hotplace get(int no) throws Exception;

  int update(Hotplace hotplace) throws Exception;

  int delete(int no) throws Exception;

}