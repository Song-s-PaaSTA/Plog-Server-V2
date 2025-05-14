package com.songspasssta.reportservice.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface S3Port {
    String uploadFile(String dirName, String fileName, MultipartFile file);
    void deleteFile(String fileUrl);
}
