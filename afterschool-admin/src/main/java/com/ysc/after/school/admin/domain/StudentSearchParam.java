package com.ysc.after.school.admin.domain;

import lombok.Data;

/**
 * 학생 검색 조건
 * 
 * @author hgko
 *
 */
@Data
public class StudentSearchParam {

	private String school;
	
	private String grade;
}
