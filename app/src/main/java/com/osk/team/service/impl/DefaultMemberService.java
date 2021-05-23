package com.osk.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.osk.team.domain.Photo;
import com.osk.team.dao.MemberDao;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;

public class DefaultMemberService implements MemberService {

  MemberDao memberDao;

  public DefaultMemberService(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  // 등록 업무
  @Override
  public int add(Member member) throws Exception {
    return memberDao.insert(member);
  }

  // 조회 업무
  @Override
  public List<Member> list(String keyword) throws Exception {
    return memberDao.findByKeyword(keyword);
  }

  // 상세 조회 업무
  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  /*@Override
  public Member getMemberno() throws Exception {//
    return memberDao.memberno();
  }*/

  // 사용자 조회 업무
  @Override
  public Member get(String email, String password) throws Exception {
    Map<String,Object> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    return memberDao.findByEmailPassword(params);
  }

  // 변경 업무
  @Override
  public int update(Member member) throws Exception {
    return memberDao.update(member);
  }

  /* @Override
  public int deletePhoto(int no) throws Exception {
    return MemberDao.deletePhoto(no);
  }*/

  // 삭제 업무
  @Override
  public int delete(int no) throws Exception {
    return memberDao.delete(no);
  }

  @Override
  public Member search(String name) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }


  /*@Override
  public int updatePhoto(int no, String photo) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int addWithPhoto(Photo photo) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }*/



}