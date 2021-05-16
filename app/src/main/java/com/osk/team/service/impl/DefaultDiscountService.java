package com.osk.team.service.impl;

import java.util.List;
import com.osk.team.dao.DiscountDao;
import com.osk.team.domain.Discount;
import com.osk.team.service.DiscountService;

public class DefaultDiscountService implements DiscountService {

  DiscountDao discountDao;

  public DefaultDiscountService(DiscountDao discountDao) {
    this.discountDao = discountDao;
  }

  //핫플레이스 등록 업무
  @Override
  public int add(Discount discount) throws Exception {
    return discountDao.insert(discount);
  }

  //핫플레이스 목록 전체 조회 업무
  @Override
  public List<Discount> list() throws Exception {
    return discountDao.findByKeyword(null);
  }

  //    //핫플레이스 상세 조회 업무
  //    @Override
  //    public Hotplace get(int no) throws Exception {
  //        Hotplace hotplace = hotplaceDao.findByNo(no);
  //        return hotplace;
  //    }


  @Override
  public Discount get(int no) throws Exception {
    Discount discount = discountDao.findByNo(no);
    return discount; 
  }

  //핫플레이스 수정 업무
  @Override
  public int update(Discount discount) throws Exception {
    return discountDao.update(discount);
  }

  //핫플에이스 삭제 업무
  @Override
  public int delete(int no) throws Exception {
    return discountDao.delete(no);
  }
}