package com.guise.guiseproject.dto;

import com.guise.guiseproject.enumeration.ImageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageDetailDto {

    private Long id;

    private String name;

    private ImageTypeEnum type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
