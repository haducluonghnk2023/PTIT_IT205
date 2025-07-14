package com.data.session05.modal.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
public class DataResponse<T> {
    private T data;
    private int status;

    public DataResponse(T data, HttpStatus status) {
        this.data = data;
        this.status = status.value();
    }
}
