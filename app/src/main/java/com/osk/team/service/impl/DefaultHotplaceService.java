package com.osk.team.service.impl;

import java.util.List;
import com.osk.team.dao.HotplaceDao;
import com.osk.team.domain.Hotplace;
import com.osk.team.service.HotplaceService;

public class DefaultHotplaceService implements HotplaceService {

  HotplaceDao hotplaceDao;

  public DefaultHotplaceService(HotplaceDao hotplaceDao) {
    this.hotplaceDao = hotplaceDao;
  }

  @Override
  public int add(Hotplace hotplace) throws Exception {
    return hotplaceDao.insert(hotplace);
  }

  @Override
  public List<Hotplace> list() throws Exception {
    return hotplaceDao.findByKeyword(null);
  }

  @Override
  public Hotplace get(int no) throws Exception {
    Hotplace hotplace = hotplaceDao.findByNo(no);
    if (hotplace != null) {
      hotplaceDao.updateViewCount(no);
    }
    return hotplace;
  }

  @Override
  public int update(Hotplace hotplace) throws Exception {
    return hotplaceDao.update(hotplace);
  }

  @Override
  public int delete(int no) throws Exception {
    return hotplaceDao.delete(no);
  }

  @Override
  public List<Hotplace> search(String keyword) throws Exception {
    return hotplaceDao.findByKeyword(keyword);
  }
}