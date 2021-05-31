package com.osk.team.dao;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.domain.Photo;

import java.util.List;
import java.util.Map;

public interface ClubDao {

    int insert(Club club) throws Exception;

    Club findByNo(int no) throws Exception;

    int update(Club club) throws Exception;

    int delete(int cno) throws Exception;

    Club clubcno() throws Exception;//오토 인크리먼츠 값을 꺼내기 위해 만듦

    List<Club> findByKeyword(String keyword) throws Exception;//보류

    List<Club> findByKeywords(Map<String, Object> params) throws Exception;

    //사진 관련
    int insertPhoto(Photo photo) throws Exception;

    List<Member> findMembers(int clubNo) throws Exception;

    int insertMember(Map<String, Object> params) throws Exception;

    int insertReport(Map<String, Object> params) throws Exception;//신고기능 추가

    int deleteMember(int memberNo) throws Exception;

    List<Club> findByReports(String keyword) throws Exception;


/////////////////////////////////////////////////////////////////////

    int deletePhotos(int clubNo) throws Exception;

    List<Photo> findPhotos(int clubNo) throws Exception;

//    int insertMember(Member member) throws Exception;

    int insertMembers(Map<String, Object> params) throws Exception;

}