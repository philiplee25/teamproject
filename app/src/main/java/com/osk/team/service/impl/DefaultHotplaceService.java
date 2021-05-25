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

  //핫플레이스 등록 업무
  @Override
  public int add(Hotplace hotplace) throws Exception {
    return hotplaceDao.insert(hotplace);
  }

  //핫플레이스 목록 전체 조회 업무
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


  //핫플레이스 수정 업무
  @Override
  public int update(Hotplace hotplace) throws Exception {
    return hotplaceDao.update(hotplace);
  }

  //핫플에이스 삭제 업무
  @Override
  public int delete(int no) throws Exception {
    return hotplaceDao.delete(no);
  }
}