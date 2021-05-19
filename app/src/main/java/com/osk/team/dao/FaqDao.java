package com.osk.team.dao;

import java.util.List;
import com.osk.team.domain.Faq;

public interface FaqDao {

  int insert(Faq faq) throws Exception;

  List<Faq> findByKeyword(String keyword) throws Exception;

  Faq findByNo(int no) throws Exception;

  int update(Faq faq) throws Exception;

  int delete(int dno) throws Exception;

}