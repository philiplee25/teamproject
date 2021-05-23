package com.osk.team.service;

import com.osk.team.domain.Member;

import java.util.List;

public interface MemberService {

  int add(Member member) throws Exception;

  List<Member> list(String keyword) throws Exception;

  //int addWithPhoto(Photo photo) throws Exception;

  //Member getMemberMno() throws Exception;//오트 인트리먼츠 값을 꺼내기 위해 만듦

  Member get(int no) throws Exception;

  Member get(String email, String password) throws Exception;

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

  Member search(String name) throws Exception;

  //int deletePhoto(int no) throws Exception;

  //int updatePhoto(int no, String photo) throws Exception;

}