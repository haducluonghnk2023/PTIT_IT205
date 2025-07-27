package com.data.session14.controller;

import com.data.session14.modal.dto.req.ShowTimeRequest;
import com.data.session14.modal.dto.res.APIResponse;
import com.data.session14.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/showtimes")
public class ShowTimeAdminController {
    @Autowired
    private ShowTimeService  service;

    @PostMapping
    public ResponseEntity<?> postSt(@RequestBody ShowTimeRequest strq){
        return ResponseEntity.ok(new APIResponse<>("Thêm lịch chiếu thành công",service.createShowTime(strq),true, HttpStatus.CREATED));
    }
}
