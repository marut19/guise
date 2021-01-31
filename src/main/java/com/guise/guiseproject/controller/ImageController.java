package com.guise.guiseproject.controller;

import com.guise.guiseproject.dto.ImageDetailDto;
import com.guise.guiseproject.dto.ImageUploadDto;
import com.guise.guiseproject.enumeration.ImageTypeEnum;
import com.guise.guiseproject.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Image Controller contains all the api
 * endpoints related to Image operations.
 */
@Validated
@RestController
@RequestMapping(value = "/api/v1/image")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ImageUploadDto uploadImage(@RequestParam("image") MultipartFile file,
                                      @RequestParam("type") @NotNull ImageTypeEnum imageType,
                                      @RequestParam("name") @NotEmpty String imageName) {
        return imageService.uploadImage(file, imageType, imageName);
    }

    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ImageDetailDto> getPreSignedURL() {
        return imageService.getImageDetailList();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable(value = "id") @Min(1) @NotNull Long id) {
        imageService.deleteImage(id);
    }

    @GetMapping(value = "/search", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public byte[] getByName(@NotEmpty @RequestParam("name") String imageName) {
        return imageService.getImageByName(imageName).getData();
    }

}
