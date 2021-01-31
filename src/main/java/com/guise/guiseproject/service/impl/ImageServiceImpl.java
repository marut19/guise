package com.guise.guiseproject.service.impl;

import com.guise.guiseproject.dto.ImageDetailDto;
import com.guise.guiseproject.dto.ImageUploadDto;
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

/**
 * Image service impl contains all the logic implementation of Image service methods
 */
@AllArgsConstructor
@Transactional
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    /**
     * This method saves an image record in the database
     *
     * @param file:multipart file
     * @param imageType: type of image
     * @param imageName: name of image
     * @return saved image record details
     */
    @Override
    @SneakyThrows
    public ImageUploadDto uploadImage(MultipartFile file, ImageTypeEnum imageType, String imageName) {
        if (!imageType.getValue().equals(file.getContentType())) {
            throw new UnProcessableEntityException("Image's image type and image type entered not matched");
        }
        if(isImageExist(imageName)) {
            throw new UnProcessableEntityException("Image by the given name already exist");
        }
        Image request = new Image();
        request.setData(file.getBytes());
        request.setType(imageType);
        request.setName(imageName);

        Image response = imageRepository.save(request);
        return new ImageUploadDto(response.getId(), response.getName(), response.getType());
    }

    /**
     * This method gives the list of image records
     * sorted on the basis of recent first
     *
     * @return list of image record
     */
    @Override
    public List<ImageDetailDto> getImageDetailList() {
        List<ImageDetailDto> responseList = new ArrayList<>();
        List<Image> imageDataList = imageRepository.findAll(Sort.by("createdAt").descending());
        ImageDetailDto imageDetailDto;
        for (Image image : imageDataList) {
            imageDetailDto = new ImageDetailDto(image.getId(), image.getName(), image.getType(), image.getCreatedAt(), image.getUpdatedAt());
            responseList.add(imageDetailDto);
        }
        return responseList;
    }

    /**
     * This method is to delete an image record
     *
     * @param id image record id
     */
    @Override
    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No image exist for the requested Id" + id));
        imageRepository.delete(image);
    }

    /**
     * This method is to fetch image record by name
     *
     * @param imageName: image name
     * @return byte array of image
     */
    @Override
    public Image getImageByName(String imageName) {
        Image image = imageRepository.getByName(imageName);
        if (image == null) {
            throw new NotFoundException("Image not found for the given image " + imageName);
        }
        return image;
    }

    private boolean isImageExist(String name) {
        return imageRepository.getByName(name) != null;
    }
}
