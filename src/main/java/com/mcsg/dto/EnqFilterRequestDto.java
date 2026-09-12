package com.mcsg.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class EnqFilterRequestDto {
    private String classMode;
    private Integer courseId;
    private String enqStatus;
	public String getClassMode() {
		return classMode;
	}
	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getEnqStatus() {
		return enqStatus;
	}
	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}
    
}
