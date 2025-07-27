package com.data.session14.service;

import com.data.session14.modal.dto.req.ShowTimeRequest;
import com.data.session14.modal.entity.ShowTime;

import java.util.List;

public interface ShowTimeService {
    List<ShowTime>  getShowTimes();
    ShowTime createShowTime(ShowTimeRequest  request);
}
