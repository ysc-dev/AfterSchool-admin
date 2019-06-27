package com.ysc.after.school.admin.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.after.school.admin.domain.db.SubjectGroup;
import com.ysc.after.school.admin.service.SchoolService;
import com.ysc.after.school.admin.service.SubjectGroupService;

/**
 * 과목 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("subject")
public class SubjectController {
	
	@Autowired
	private SubjectGroupService subjectGroupService;
	
	@Autowired
	private SchoolService schoolService;

	/**
	 * 과목 그룹 화면
	 * @param model
	 */
	@GetMapping("groupList")
	public void groupList(Model model) {
	}
	
	/**
	 * 과목 그룹 조회
	 * @return
	 */
	@PostMapping("group/search")
	@ResponseBody 
	public ResponseEntity<?> search() {
		return new ResponseEntity<>(subjectGroupService.getList(), HttpStatus.OK);
	}
	
	/**
	 * 과목 그룹 정보 불러오기
	 * @param id
	 * @return
	 */
	@GetMapping("group/get")
	@ResponseBody 
	public SubjectGroup getSubjectGroup(int id) {
		return subjectGroupService.get(id);
	}
	
	/**
	 * 과목 그룹 등록
	 * @param subjectGroup
	 * @return
	 */
	@PostMapping("group/regist")
	@ResponseBody
	public ResponseEntity<?> registSubjectgroup(SubjectGroup subjectGroup) {
		if (subjectGroupService.regist(subjectGroup)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 과목 그룹 수정
	 * @param subjectGroup
	 * @return
	 */
	@PutMapping("group/update")
	@ResponseBody
	public ResponseEntity<?> updateSubjectgroup(SubjectGroup subjectGroup) {
		if (subjectGroupService.update(subjectGroup)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 과목 그룹 삭제
	 * @param subjectGroup
	 * @return
	 */
	@DeleteMapping("group/delete")
	@ResponseBody
	public ResponseEntity<?> deleteSubjectgroup(int id) {
		if (subjectGroupService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("schools", schoolService.getList().stream().map(data -> {
			if (data.getName().contains("분교")) {
				return data.getName();
			}
			return data.getName() + data.getSchoolType().getName();
		}).sorted().collect(Collectors.toList()));
	}
}
