package com.mcsg.service;


import com.mcsg.dto.DashboardResponseDto;
import com.mcsg.entity.Counsellor;
import com.mcsg.entity.Enquiry;
import com.mcsg.repo.CounsellorRepo;
import com.mcsg.repo.EnquiryRepo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
@Service
public class CounsellorServiceImpl implements CounsellorService{
    private CounsellorRepo counsellorRepo   ;
    private EnquiryRepo enquiryRepo;

    public CounsellorServiceImpl(CounsellorRepo counsellorRepo,EnquiryRepo enquiryRepo){
        this.counsellorRepo = counsellorRepo;
        this.enquiryRepo = enquiryRepo;
    }
    @Override
    public boolean saveCounsellor(Counsellor counsellor){
        try{
            counsellorRepo.save(counsellor);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public Counsellor login(String email, String password) {
    	Optional<Counsellor> opt=counsellorRepo.findByEmailAndPwd(email, password);
    	return opt.orElse(null);
    }
    @Override
    public DashboardResponseDto getDashboardInfo(Integer counsellorId) {
    	List<Enquiry> enquiryList=enquiryRepo.findByCounsellorCounsellorId(counsellorId);
    	int totalEnqsCount=enquiryList.size();
    	
    	Map<String, Long> statusWiseCountMap=enquiryList.stream()
    			.collect(Collectors.groupingBy(Enquiry::getEnqStatus,Collectors.counting()));
    	
    	int newEnqsCount=statusWiseCountMap.getOrDefault("NEW", 0L).intValue();
    	int enrolledEnqsCount=statusWiseCountMap.getOrDefault("ENROLLED", 0L).intValue();
    	int lostEnqsCount=statusWiseCountMap.getOrDefault("LOST", 0L).intValue();
    	
    	DashboardResponseDto dto=new DashboardResponseDto();
        dto.setEnrolledEnqs(enrolledEnqsCount);
        dto.setLostEnqs(lostEnqsCount);
        dto.setNewEnqs(newEnqsCount);
        dto.setTotalEnqs(totalEnqsCount);
        return dto;
    }
    
}

