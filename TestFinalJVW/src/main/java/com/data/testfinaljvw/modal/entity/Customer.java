package com.data.testfinaljvw.modal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",length = 50,unique = true,nullable = false)
    private String username;
    @Column(name = "full_name",length = 50)
    private String fullName;
    @Column(length = 100)
    private String password;
    @Column(name = "email",length = 50,nullable = false,unique = true)
    private String email;
    @Column(name = "phone",length = 50,unique = true)
    private String phone;
    private Boolean isLogin;
    private Boolean status;
}
