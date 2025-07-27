package com.data.session14.controller;

import com.data.session14.modal.dto.res.APIResponse;
import com.data.session14.modal.entity.ShowTime;
import com.data.session14.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/showtimes")
public class ShowTimePublicController {
    @Autowired
    private ShowTimeService  service;

    @GetMapping
    public ResponseEntity<APIResponse<List<ShowTime>>>  getShowTimes(){
        return ResponseEntity.ok(new APIResponse<>("Lấy danh sách lịch chiếu thành công",service.getShowTimes(),true, HttpStatus.OK));
    }
}
