package com.guise.guiseproject.service;

import com.guise.guiseproject.dto.ImageDetailDto;
import com.guise.guiseproject.dto.ImageUploadDto;
import com.guise.guiseproject.enumeration.ImageTypeEnum;
import com.guise.guiseproject.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Image Service interface contains all the abstract Image operation methods.
 */
public interface ImageService {

    ImageUploadDto uploadImage(MultipartFile file, ImageTypeEnum imageType, String imageName);

    List<ImageDetailDto> getImageDetailList();

    void deleteImage(Long id);

    Image getImageByName(String imageName);

}
