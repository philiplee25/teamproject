package com.osk.team.service.impl;

import java.util.List;
import com.osk.team.dao.FaqDao;
import com.osk.team.domain.Faq;
import com.osk.team.service.FaqService;
import org.springframework.stereotype.Service;

@Service
public class DefaultFaqService implements FaqService {

  FaqDao faqDao;

  public DefaultFaqService(FaqDao faqDao) {
    this.faqDao = faqDao;
  }

  @Override
  public int add(Faq faq) throws Exception {
    return faqDao.insert(faq);
  }

  @Override
  public List<Faq> list() throws Exception {
    return faqDao.findByKeyword(null);
  }

  @Override
  public Faq get(int no) throws Exception {
    Faq faq = faqDao.findByNo(no);
    return faq; 
  }

  @Override
  public int update(Faq faq) throws Exception {
    return faqDao.update(faq);
  }

  @Override
  public int delete(int no) throws Exception {
    return faqDao.delete(no);
  }

  @Override
  public List<Faq> search(String keyword) throws Exception {
    return faqDao.findByKeyword(keyword);
  }
}