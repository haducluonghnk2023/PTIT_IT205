package com.data.session14.modal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeRequest {
    private Long movieId;
    private Date startTime;
    private String room;
}
