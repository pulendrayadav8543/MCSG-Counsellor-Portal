package com.mcsg.service;

import com.mcsg.dto.DashboardResponseDto;
import com.mcsg.entity.Counsellor;

public interface CounsellorService {
    public boolean saveCounsellor(Counsellor counsellor);

    public Counsellor login(String email,String pwd);

    public DashboardResponseDto getDashboardInfo(Integer counsellorId);

}
