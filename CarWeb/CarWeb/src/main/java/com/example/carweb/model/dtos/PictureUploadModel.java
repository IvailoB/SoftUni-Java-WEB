package com.example.carweb.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
public class PictureUploadModel {

    Long id;

    @NotNull
    private MultipartFile picture;
}
