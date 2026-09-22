package com.mcsg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mcsg.constants.AppConstants;
import com.mcsg.dto.EnqFilterRequestDto;
import com.mcsg.dto.EnquiryDto;
import com.mcsg.entity.Enquiry;
import com.mcsg.service.CourseService;
import com.mcsg.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/enquiry")
	public String returnEnqForm(Model model) {
		model.addAttribute(AppConstants.ENQ_DTO, new EnquiryDto());
		model.addAttribute(AppConstants.COURSES, courseService.getCourses());
		return "add-enq";
	}
	
	@PostMapping("/enquiry")
	public String saveOrUpdateEnquiry(@ModelAttribute("enquiryDto") EnquiryDto enqDto, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("cid");
		
		if (enqDto.getEnqId() != null) {
			boolean isUpdated = enquiryService.updateEnquiry(enqDto);
			
			if (isUpdated) {
				model.addAttribute("smsg", "Enquiry Updated Successfully!");
			} else {
				model.addAttribute("emsg", "Failed to Update Enquiry");
			}
			model.addAttribute(AppConstants.COURSES, courseService.getCourses());
			return "edit-enq";
		} else {
		
			boolean isSaved = enquiryService.addEnquiry(enqDto, counsellorId);
			
			if (isSaved) {
				model.addAttribute("success", "Enquiry Added");
			} else {
				model.addAttribute("error", "Failed to add enquiry");
			}
			model.addAttribute(AppConstants.ENQ_DTO, new EnquiryDto());
			model.addAttribute(AppConstants.COURSES, courseService.getCourses());
			return "add-enq";
		}
	}
	
	@GetMapping("/view-enquiries")
	public String viewEnquiries(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("cid");
		
		List<Enquiry> allEnquiries = enquiryService.getAllEnquiries(counsellorId);
		model.addAttribute("enqs", allEnquiries);
		model.addAttribute("filter", new EnqFilterRequestDto());
		model.addAttribute(AppConstants.COURSES, courseService.getCourses());
		
		return "view-enqs";
	}
	
	@PostMapping("/filter-enqs")
	public String filterEnquiries(EnqFilterRequestDto filter, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("cid");
		
		List<Enquiry> allEnquiries = enquiryService.getEnquiriesWithFilter(filter, counsellorId);
		model.addAttribute("enqs", allEnquiries);
		model.addAttribute("filter", new EnqFilterRequestDto());
		model.addAttribute(AppConstants.COURSES, courseService.getCourses());
		return "view-enqs";
	}
	
	@GetMapping("/edit-enq")
	public String editEnquiry(@RequestParam Integer enqId, Model model) {
		EnquiryDto enquiryDto = enquiryService.editEnquiry(enqId);
		model.addAttribute(AppConstants.ENQ_DTO, enquiryDto);
		model.addAttribute(AppConstants.COURSES, courseService.getCourses());
		return "edit-enq";
	}
}