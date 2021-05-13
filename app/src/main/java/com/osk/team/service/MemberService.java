package com.osk.team.service;

import com.osk.team.domain.Member;

import java.util.List;

public interface MemberService {
    int add(Member member) throws Exception;

//    List<Member> findByMember(int no) throws Exception;

    Member get(String email, String password) throws Exception;

//    Member get(int no) throws Exception;

    int update(Member member) throws Exception;

    int delete(int no) throws Exception;

    //탈퇴 기능
}
