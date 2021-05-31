package com.osk.team.service;

import java.util.List;
import com.osk.team.domain.Qna;

public interface QnaService {

    int add(Qna qna) throws Exception;

    List<Qna> listAll() throws Exception;

    Qna get(int no) throws Exception;

    int update(Qna qna) throws Exception;

    int delete(int no) throws Exception;

    List<Qna> search(String keyword) throws Exception;

}
