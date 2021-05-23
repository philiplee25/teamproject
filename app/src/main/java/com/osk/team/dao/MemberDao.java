package com.osk.team.dao;

import com.osk.team.domain.Member;

import java.util.List;
import java.util.Map;

public interface MemberDao {

  int insert(Member member) throws Exception;

  //int insertPhoto(Photo photo) throws Exception;

  //int updatePhoto(Photo photo) throws Exception;

  //static int deletePhoto(int no) throws Exception {
  // TODO Auto-generated method stub
  //  return 0;
  //}

  Member membermno() throws Exception;

  List<Member> list(String keyword) throws Exception;

  List<Member> findByKeyword(String keyword) throws Exception;

  Member findByNo(int no) throws Exception;

  Member findByEmailPassword(Map<String,Object> params) throws Exception;

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

  Member findByName(String name) throws Exception;


}