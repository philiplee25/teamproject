package com.osk.team.service.impl;

import com.osk.mybatis.TransactionCallback;
import com.osk.mybatis.TransactionManager;
import com.osk.mybatis.TransactionTemplate;
import com.osk.team.dao.ClubDao;
import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.domain.Photo;
import com.osk.team.service.ClubService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DefaultClubService implements ClubService {

    TransactionTemplate transactionTemplate;
    ClubDao clubDao;

    public DefaultClubService(TransactionManager txManager, ClubDao clubDao) {
        this.transactionTemplate = new TransactionTemplate(txManager);
        this.clubDao = clubDao;
    }

    @Override
    public int add(Club club) throws Exception {
        return clubDao.insert(club);
    }

    @Override
    public List<Club> list() throws Exception {
        return clubDao.findByKeyword(null);
    }

    @Override
    public Club get(int no) throws Exception {
        return clubDao.findByNo(no);
    }

    @Override
    public Club getClubCno() throws Exception {//
        return clubDao.clubcno();
    }

    @Override
    public int addWithPhoto(Photo photo) throws Exception {
        return clubDao.insertPhoto(photo);
    }

    @Override
    public List<Photo> getPhotos(int clubNo) throws Exception {
        return clubDao.findPhotos(clubNo);
    }

    @Override
    public int update(Club club) throws Exception {
//        return (int) transactionTemplate.execute(new TransactionCallback() {
//            @Override
//            public Object doInTransaction() throws Exception {
//                int count = clubDao.update(club);
//                clubDao.deletePhotos(club.getNo());
//
//                if (club.getPhotos().size() > 0) {
//                    clubDao.insertPhoto(new Photo());
//                    //멤버 수정도 추가해야됨(강퇴)
//                }
//                return count;
//            }
//        });
        return clubDao.update(club);
    }

    @Override
    public int delete(int no) throws Exception {
        return clubDao.delete(no);
    }

    @Override
    public List<Club> search(String arrive, String startDate, String endDate, String theme) throws Exception {//새로 만듬
        HashMap<String, Object> params = new HashMap<>();
        params.put("arrive", arrive);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("theme", theme);

        return clubDao.findByKeywords(params);
    }

    @Override
    public List<Club> search(String keyword) throws Exception {
        return clubDao.findByKeyword(keyword);
    }

    @Override
    public int deletePhotos(int clubNo) throws Exception {
        return clubDao.deletePhotos(clubNo);
    }

    @Override
    public int updatePhotos(int clubNo, List<Photo> photos) throws Exception {
        return (int) transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction() throws Exception {
                clubDao.deletePhotos(clubNo);

                HashMap<String,Object> params = new HashMap<>();
                params.put("clubNo", clubNo);
                params.put("photos", photos);

                return clubDao.insertMember(params);
            }
        });
    }


    //현재 인원 관리파트
    @Override
    public int deleteMember(int clubNo) throws Exception {
        return clubDao.deleteMembers(clubNo);
    }

    @Override
    public int updateMember(int clubNo, List<Member> members) throws Exception {
        return (int) transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction() throws Exception {
                clubDao.deleteMembers(clubNo);

                HashMap<String,Object> params = new HashMap<>();
                params.put("clubNo", clubNo);
                params.put("members", members);

                return clubDao.insertMember(params);
            }
        });
    }
}
