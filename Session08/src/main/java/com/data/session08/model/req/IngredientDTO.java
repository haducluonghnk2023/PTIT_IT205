package com.data.session08.model.req;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDTO {
    private String name;
    private Integer stock;
    private String expiry;
    private MultipartFile image;
}
