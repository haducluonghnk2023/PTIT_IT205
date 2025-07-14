package com.data.session05.modal.dto.res;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResDTO {
    private String fullName;
    private String gender;
    private String address;
    private String className;
}
