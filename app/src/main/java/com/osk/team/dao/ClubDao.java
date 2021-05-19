package com.osk.team.dao;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.domain.Photo;

import java.util.Date;
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
    List<Photo> findPhotos(int clubNo) throws Exception;

    int insertPhoto(Photo photo) throws Exception;

//    int updatePhoto(Photo photo) throws Exception;

//    int insertPhotos(Photo photo) throws Exception;

//    int insertPhotos(Map<String, Object> params) throws Exception;

    int deletePhotos(int clubNo) throws Exception;


    //멤버관련
    List<Member> findMembers(int clubNo) throws Exception;

    int insertMember(Map<String, Object> params) throws Exception;

    int insertMembers(Map<String, Object> params) throws Exception;

    int deleteMembers(int memberNo) throws Exception;

}