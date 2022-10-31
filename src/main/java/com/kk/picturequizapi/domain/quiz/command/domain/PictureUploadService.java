package com.kk.picturequizapi.domain.quiz.command.domain;

import org.springframework.web.multipart.MultipartFile;

public interface PictureUploadService {

    Picture uploadPicture(MultipartFile multipartFile);
}
