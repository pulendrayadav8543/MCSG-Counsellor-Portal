package com.mcsg.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mcsg.dto.EnqFilterRequestDto;
import com.mcsg.dto.EnquiryDto;
import com.mcsg.entity.Counsellor;
import com.mcsg.entity.Course;
import com.mcsg.entity.Enquiry;
import com.mcsg.repo.CounsellorRepo;
import com.mcsg.repo.CourseRepo;
import com.mcsg.repo.EnquiryRepo;
@Service
public class EnquiryServiceImpl implements EnquiryService{
	private CounsellorRepo counsellorRepo;
	private EnquiryRepo enqRepo;
	private CourseRepo courseRepo;

	public EnquiryServiceImpl(CounsellorRepo counsellorRepo,EnquiryRepo enqRepo,CourseRepo courseRepo) {
		this.counsellorRepo=counsellorRepo;
		this.courseRepo=courseRepo;
		this.enqRepo=enqRepo;
	}

	@Override
	public boolean addEnquiry(EnquiryDto enqDto, Integer counsellorId) {
		
		Enquiry enquiry=new Enquiry();
		BeanUtils.copyProperties(enqDto, enquiry);
		
		 Course course = courseRepo.findById(enqDto.getCourseId()).get();
		Counsellor counsellor=counsellorRepo.findById(counsellorId).get();
		
		enquiry.setCourse(course);
		enquiry.setCounsellor(counsellor);
		
		try {
			enqRepo.save(enquiry);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer counsellorId) {
		return enqRepo.findByCounsellorCounsellorId(counsellorId);
	}

	@Override
	public List<Enquiry> getEnquiriesWithFilter(EnqFilterRequestDto reqDto, Integer counsellorId) {
	    Enquiry enqEntity = new Enquiry();

	    
	    if (reqDto.getClassMode() != null && !reqDto.getClassMode().trim().isEmpty()) {
	        enqEntity.setClassMode(reqDto.getClassMode());
	    }

	    if (reqDto.getEnqStatus() != null && !reqDto.getEnqStatus().trim().isEmpty()) {
	        enqEntity.setEnqStatus(reqDto.getEnqStatus());
	    }

	    if (reqDto.getCourseId() != null) {
	        courseRepo.findById(reqDto.getCourseId()).ifPresent(enqEntity::setCourse);
	    }

	    if (counsellorId != null) {
	        counsellorRepo.findById(counsellorId).ifPresent(enqEntity::setCounsellor);
	    }

	    return enqRepo.findAll(Example.of(enqEntity));
	}

	@Override
	public EnquiryDto editEnquiry(Integer enqId) {
	    Enquiry enquiry = enqRepo.findById(enqId).orElse(null);
	    if (enquiry == null) return null;

	    EnquiryDto dto = new EnquiryDto();
	    dto.setEnqId(enquiry.getEnqId());
	    dto.setStuName(enquiry.getStuName());
	    dto.setStuPhno(enquiry.getStuPhno());
	    dto.setClassMode(enquiry.getClassMode());
	    dto.setEnqStatus(enquiry.getEnqStatus());
	    
	    if (enquiry.getCourse() != null) {
	        dto.setCourseId(enquiry.getCourse().getCourseid()); // Essential for drop-down binding
	    }
	    
	    return dto;
	}

	@Override
	public boolean updateEnquiry(EnquiryDto enqDto) {
	    if (enqDto == null || enqDto.getEnqId() == null) {
	        return false;
	    }

	    java.util.Optional<Enquiry> enqOpt = enqRepo.findById(enqDto.getEnqId());
	    if (enqOpt.isPresent()) {
	        Enquiry enquiry = enqOpt.get();
	        
	        
	        enquiry.setStuName(enqDto.getStuName());
	        enquiry.setClassMode(enqDto.getClassMode());
	        enquiry.setStuPhno(enqDto.getStuPhno());
	        enquiry.setEnqStatus(enqDto.getEnqStatus());
	        
	        
	        if (enqDto.getCourseId() != null) {
	            courseRepo.findById(enqDto.getCourseId()).ifPresent(enquiry::setCourse);
	        }
	        
	        enqRepo.save(enquiry);
	        return true;
	    }
	    return false;
	}
}
