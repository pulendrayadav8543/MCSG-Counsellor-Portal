package com.mcsg.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DashboardResponseDto {
    private Integer totalEnqs;
    private Integer newEnqs;
    private Integer enrolledEnqs;
    private Integer lostEnqs;
	public Integer getTotalEnqs() {
		return totalEnqs;
	}
	public void setTotalEnqs(Integer totalEnqs) {
		this.totalEnqs = totalEnqs;
	}
	public Integer getNewEnqs() {
		return newEnqs;
	}
	public void setNewEnqs(Integer newEnqs) {
		this.newEnqs = newEnqs;
	}
	public Integer getEnrolledEnqs() {
		return enrolledEnqs;
	}
	public void setEnrolledEnqs(Integer enrolledEnqs) {
		this.enrolledEnqs = enrolledEnqs;
	}
	public Integer getLostEnqs() {
		return lostEnqs;
	}
	public void setLostEnqs(Integer lostEnqs) {
		this.lostEnqs = lostEnqs;
	}
    
}

