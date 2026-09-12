package com.mcsg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcsg.entity.Course;
import com.mcsg.repo.CourseRepo;
@Service
public class CouserServiceImpl implements CourseService{
	@Autowired
	private CourseRepo courseRepo;
	
	@Override
	public List<Course> getCourses(){
		return courseRepo.findAll();
	}

}
