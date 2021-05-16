package com.osk.team.service;

import com.osk.team.domain.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> list(String keyword) throws Exception;
}