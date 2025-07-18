package com.data.session08.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "username",length = 100,nullable = false,unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "fullname",length = 100,nullable = false)
    private String fullname;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "address",length = 200)
    private String address;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "phone",length = 20,nullable = false)
    private String phone;

    private String confirmPassword;
}
