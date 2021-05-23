package com.osk.team.service;

import com.osk.team.domain.Faq;

import java.util.List;

public interface FaqService {

  int add(Faq faq) throws Exception;

  List<Faq> list() throws Exception;

  Faq get(int no) throws Exception;

  int update(Faq faq) throws Exception;

  int delete(int no) throws Exception;

  List<Faq> search(String keyword) throws Exception;
}