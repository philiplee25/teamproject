package com.osk.team.service.impl;

import java.util.List;
import com.osk.team.dao.FaqDao;
import com.osk.team.domain.Faq;
import com.osk.team.service.FaqService;

public class DefaultFaqService implements FaqService {

  FaqDao faqDao;

  public DefaultFaqService(FaqDao faqDao) {
    this.faqDao = faqDao;
  }

  //핫플레이스 등록 업무
  @Override
  public int add(Faq faq) throws Exception {
    return faqDao.insert(faq);
  }

  //핫플레이스 목록 전체 조회 업무
  @Override
  public List<Faq> list() throws Exception {
    return faqDao.findByKeyword(null);
  }

  //    //핫플레이스 상세 조회 업무
  //    @Override
  //    public Hotplace get(int no) throws Exception {
  //        Hotplace hotplace = hotplaceDao.findByNo(no);
  //        return hotplace;
  //    }


  // 게시글 상세 조회 업무
  @Override
  public Faq get(int no) throws Exception {
    Faq faq = faqDao.findByNo(no);
    return faq; 
  }

  //핫플레이스 수정 업무
  @Override
  public int update(Faq faq) throws Exception {
    return faqDao.update(faq);
  }

  //핫플에이스 삭제 업무
  @Override
  public int delete(int no) throws Exception {
    return faqDao.delete(no);
  }

  @Override
  public List<Faq> search(String keyword) throws Exception {
    return faqDao.findByKeyword(keyword);
  }
}