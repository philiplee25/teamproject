package com.osk.team.service.impl;

import com.osk.team.dao.ClubDao;
import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.domain.Photo;
import com.osk.team.service.ClubService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultClubService implements ClubService {


    TransactionTemplate transactionTemplate;

    ClubDao clubDao;

    public DefaultClubService(PlatformTransactionManager txManager, ClubDao clubDao) {
        this.transactionTemplate = new TransactionTemplate(txManager);
        this.clubDao = clubDao;
    }

    @Override
    public int add(Club club) throws Exception {
        return transactionTemplate.execute(new TransactionCallback<Integer>(){
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                try {
                    return clubDao.insert(club);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                try {
                    return clubDao.update(club);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public int delete(int no) throws Exception {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                try {
                    return clubDao.delete(no);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
    public int addWithMember(Map<String, Object> params) throws Exception {
        return clubDao.insertMember(params);
    }

    @Override
    public List<Member> getMembers(int clubNo) throws Exception {
        return clubDao.findMembers(clubNo);
    }

    @Override
    public int addWithReport(Map<String, Object> params) throws Exception {//신고 기능
        return clubDao.insertReport(params);
    }

    @Override
    public List<Club> getReports() throws Exception {//신고글 가져오기
        return clubDao.findByReports(null);
    }

    @Override
    public int deletePhotos(int clubNo) throws Exception {
        return clubDao.deletePhotos(clubNo);
    }

    //현재 인원 관리파트
    @Override
    public int deleteMember(int memberNo) throws Exception {
        return clubDao.deleteMember(memberNo);
    }

    ////////////////////////////////////////////////////////

    @Override
    public int updatePhotos(int clubNo, List<Photo> photos) throws Exception {
        return 0;
    }

    @Override
    public int updateMembers(int clubNo, List<Member> members) throws Exception {
        return 0;
    }
}
