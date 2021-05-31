package com.osk.team.service.impl;

import java.util.List;
import com.osk.team.dao.DiscountDao;
import com.osk.team.domain.Discount;
import com.osk.team.service.DiscountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class DefaultDiscountService implements DiscountService {

  DiscountDao discountDao;

  public DefaultDiscountService(DiscountDao discountDao) {
    this.discountDao = discountDao;
  }

  @Override
  public int add(Discount discount) throws Exception {
    return discountDao.insert(discount);
  }

  @Override
  public List<Discount> list() throws Exception {
    return discountDao.findByKeyword(null);
  }

  @Override
  public Discount get(int no) throws Exception {
    Discount discount = discountDao.findByNo(no);
    if (discount != null) {
      discountDao.updateViewCount(no);
    }
    return discount; 
  }

  @Override
  public int update(Discount discount) throws Exception {
    return discountDao.update(discount);
  }

  @Override
  public int delete(int no) throws Exception {
    return discountDao.delete(no);
  }

  @Override
  public List<Discount> search(String keyword) throws Exception {
    return discountDao.findByKeyword(keyword);
  }
}