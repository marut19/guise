package com.guise.guiseproject.service;

import com.guise.guiseproject.dto.ImageDetailDto;
import com.guise.guiseproject.enumeration.ImageTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    ImageDetailDto uploadImage(MultipartFile file, ImageTypeEnum imageType, String imageName);

    List<ImageDetailDto> getImageDetailList(int pageNo, int pageSize);

    void deleteImage(Long id);

    byte[] getImage(String imageName);

}
