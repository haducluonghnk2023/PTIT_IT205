package com.data.session08.model.req;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Setter
@Getter
public class DishDTO {
    private String name;
    private String description;
    private Double price;
    private String status;
    private MultipartFile image;
}

