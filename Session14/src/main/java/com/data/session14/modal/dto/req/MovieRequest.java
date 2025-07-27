package com.data.session14.modal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String title;
    private String description;
    private Integer duration;
    private Date releaseDate;
}
