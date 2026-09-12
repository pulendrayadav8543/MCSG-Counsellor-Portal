package com.mcsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mcsg.dto.DashboardResponseDto;
import com.mcsg.entity.Counsellor;
import com.mcsg.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CourseController {
	@Autowired
	private CounsellorService counsellorService;
	
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "login";
	}
	@PostMapping("/login")
	public String loginHandle(Counsellor counsellor,Model model,HttpServletRequest request) {
		Counsellor loggedCounsellor=counsellorService.login(counsellor.getEmail(),counsellor.getPwd());
		
		if(loggedCounsellor!=null) {
			HttpSession session=request.getSession(true);
			session.setAttribute("cid", loggedCounsellor.getCounsellorId());
			return "redirect:dashboard";
		}else {
			model.addAttribute("error", "Invalid Credentials");
			return "login";
		}
	}
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "register";
	}
	@PostMapping("/register")
	public String handleRegistration(Counsellor counsellor,Model model) {
		boolean isSaved=counsellorService.saveCounsellor(counsellor);
		
		if(isSaved) {
			model.addAttribute("success", "Registration Successful");
		}else {
			model.addAttribute("error", "Registration Failed");
		}
		return "register";
	}
	
	@GetMapping(value="/dashboard")
	public String buildDashboard(Model model,HttpServletRequest request) {
		HttpSession session=request.getSession(true);
		Integer counsellorId=(Integer) session.getAttribute("cid");
		
		DashboardResponseDto dashboardInfo=counsellorService.getDashboardInfo(counsellorId);
		
		model.addAttribute("dashboardInfo", dashboardInfo);
		
		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}

}
