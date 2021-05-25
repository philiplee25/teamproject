package com.osk.team.service;

import java.util.List;
import com.osk.team.domain.Discount;

public interface DiscountService {

  int add(Discount discount) throws Exception;

  List<Discount> list() throws Exception;

  Discount get(int no) throws Exception;

  int update(Discount discount) throws Exception;

  int delete(int no) throws Exception;

  List<Discount> search(String keyword) throws Exception;
}