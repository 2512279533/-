package com.guigu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);
}
