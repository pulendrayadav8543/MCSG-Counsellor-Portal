package com.mcsg.service;

import java.util.List;

import com.mcsg.dto.EnqFilterRequestDto;
import com.mcsg.dto.EnquiryDto;
import com.mcsg.entity.Enquiry;

public interface EnquiryService {
	public boolean addEnquiry(EnquiryDto enq,Integer counsellorId);
	
	public List<Enquiry> getAllEnquiries(Integer counsellorId);
	
	public List<Enquiry> getEnquiriesWithFilter(EnqFilterRequestDto reqDto,Integer counsellorId);
	
	public EnquiryDto editEnquiry(Integer enqId);
	
	public boolean updateEnquiry(EnquiryDto enqDto);

}
