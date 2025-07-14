package com.data.session05.modal.dto.res;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FruitDto {
    private Long id;
    private String name;
    private Double price;
}