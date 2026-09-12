package com.mcsg.dto;

public class EnquiryDto {
    private Integer enqId;
    private String stuName;
    private String stuPhno;
    private String classMode;
    private Integer courseId;
    private String enqStatus;
	public Integer getEnqId() {
		return enqId;
	}
	public void setEnqId(Integer enqId) {
		this.enqId = enqId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuPhno() {
		return stuPhno;
	}
	public void setStuPhno(String stuPhno) {
		this.stuPhno = stuPhno;
	}
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
