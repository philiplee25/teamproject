package com.osk.team.dao;

import java.util.List;
import com.osk.team.domain.Discount;

public interface DiscountDao {

  int insert(Discount discount) throws Exception;

  List<Discount> findByKeyword(String keyword) throws Exception;

  Discount findByNo(int no) throws Exception;

  int update(Discount discount) throws Exception;

  int updateViewCount(int no) throws Exception;

  int delete(int dno) throws Exception;

}