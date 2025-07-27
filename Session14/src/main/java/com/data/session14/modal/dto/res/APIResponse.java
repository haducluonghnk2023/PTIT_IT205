package com.data.session14.modal.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private String message;
    private T data;
    private Boolean success;
    private HttpStatus status;
}
