package com.osk.team.service;

import com.osk.team.domain.Discount;

import java.util.List;

public interface DiscountService {

  int add(Discount discount) throws Exception;

  List<Discount> list() throws Exception;

  Discount get(int no) throws Exception;

  int update(Discount discount) throws Exception;

  int delete(int no) throws Exception;

  List<Discount> search(String keyword) throws Exception;
}