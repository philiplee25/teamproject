package com.osk.team.service;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.domain.Photo;

import java.util.List;

public interface ClubService {

    int add(Club club) throws Exception;

    List<Club> list() throws Exception;

    int update(Club club) throws Exception;

    int delete(int no) throws Exception;

    Club get(int no) throws Exception;

    int addWithPhoto(Photo photo) throws Exception;

    Club getClubCno() throws Exception;//오트 인트리먼츠 값을 꺼내기 위해 만듦

    List<Club> search(String arrive, String startDate, String endDate, String theme) throws Exception;

    List<Club> search(String keyword) throws Exception;

    int addWithMember(Member member) throws Exception;//클럽 가입 멤버


    List<Photo> getPhotos(int clubNo) throws Exception;//사진 가져옴

    int deleteMembers(int clubNo) throws Exception;

    int updateMembers(int projectNo, List<Member> members) throws Exception;

    int deletePhotos(int clubNo) throws Exception;

    int updatePhotos(int projectNo, List<Photo> photos) throws Exception;

    // 로그인 된 유저가 클럽 참여 -> 멤버 추가
}
