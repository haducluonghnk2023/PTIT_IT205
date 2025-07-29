package com.data.testfinaljvw.service;

import com.data.testfinaljvw.modal.dto.req.LoginReq;
import com.data.testfinaljvw.modal.dto.req.RegisterReq;
import com.data.testfinaljvw.modal.dto.res.JWTResponse;
import com.data.testfinaljvw.modal.entity.Customer;

public interface CustomerService {
    Customer register(RegisterReq r);
    JWTResponse login(LoginReq l);
}
