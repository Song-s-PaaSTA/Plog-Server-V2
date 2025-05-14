package com.songspasssta.reportservice.application.service;

import com.songspasssta.reportservice.application.port.out.S3Port;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * S3에 업로드 및 삭제하는 서비스
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {
    private final S3Port s3Port;

    /**
     * 신고글 이미지 업로드
     */
    public String uploadFile(MultipartFile file, String folder) {
        if (file != null && !file.isEmpty()) {
            return s3Port.uploadFile(folder, file.getOriginalFilename(), file);
        }
        return null;
    }

    /**
     * 신고글 이미지 삭제
     */
    public void deleteFile(String fileUrl) {
        if (fileUrl != null) {
            s3Port.deleteFile(fileUrl);
        }
    }
}