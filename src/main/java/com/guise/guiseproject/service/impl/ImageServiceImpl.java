package com.guise.guiseproject.service.impl;

import com.guise.guiseproject.dto.ImageDetailDto;
import com.guise.guiseproject.enumeration.ImageTypeEnum;
import com.guise.guiseproject.exception.NotFoundException;
import com.guise.guiseproject.exception.UnProcessableEntityException;
import com.guise.guiseproject.model.Image;
import com.guise.guiseproject.repository.ImageRepository;
import com.guise.guiseproject.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @SneakyThrows
    public ImageDetailDto uploadImage(MultipartFile file, ImageTypeEnum imageType, String imageName) {
        if (!imageType.getValue().equals(file.getContentType())) {
            throw new UnProcessableEntityException("Image and image type not matched");
        }
        Image request = new Image();
        request.setData(file.getBytes());
        request.setType(imageType);
        request.setName(imageName);

        Image response = imageRepository.save(request);
        return new ImageDetailDto(response.getId(), response.getName(), response.getType(), response.getCreatedAt(), response.getUpdatedAt());
    }

    @Override
    public List<ImageDetailDto> getImageDetailList(int pageNo, int pageSize) {
        List<ImageDetailDto> responseList = new ArrayList<>();
        List<Image> imageDataList = imageRepository.findAll(Sort.by("createdAt").descending());
        ImageDetailDto imageDetailDto;
        for (Image image : imageDataList) {
            imageDetailDto = new ImageDetailDto(image.getId(), image.getName(), image.getType(), image.getCreatedAt(), image.getUpdatedAt());
            responseList.add(imageDetailDto);
        }
        return responseList;
    }

    @Override
    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No image exist for the requested Id" + id));
        imageRepository.delete(image);
    }

    @Override
    public byte[] getImage(String imageName) {
        Image image = imageRepository.getByName(imageName);
        if (image == null) {
            throw new NotFoundException("Image not found for the given image " + imageName);
        }
        return image.getData();
    }
}
