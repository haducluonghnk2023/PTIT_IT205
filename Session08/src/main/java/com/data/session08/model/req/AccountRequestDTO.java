package com.data.session08.model.req;

import com.data.session08.validator.ConfirmPasswordMatching;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
@ConfirmPasswordMatching(password = "password",confirmPassword = "confirmPassword")
public class AccountRequestDTO {
    @NotBlank(message = "Username is empty")
    private String username;
    @NotBlank(message = "Password is empty")
    @Length(min = 6,message = "Password has test or equals than 6 character")
    private String password;
    @NotBlank(message = "Fullname is empty")
    private String fullname;
    @NotNull(message = "Gender is empty!")
    private Boolean gender;
    @NotBlank(message = "Address is empty")
    private String address;
    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$",message = "Email not valid")
    private String email;
    @NotBlank(message = "Phone is empty")
    private String phone;
    private String confirmPassword;
}
