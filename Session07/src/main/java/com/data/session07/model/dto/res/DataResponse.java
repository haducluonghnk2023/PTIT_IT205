package com.data.session07.model.dto.res;

import com.data.session07.model.entity.Seed;
import com.data.session07.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DataResponse<T> {
    private T data;
    private HttpStatus status;

    public DataResponse(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }


}
