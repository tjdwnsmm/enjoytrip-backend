package com.discovero.enjoytrip.plan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discovero.enjoytrip.plan.model.IPlanService;
import com.discovero.enjoytrip.plan.model.PlanDto;
import com.discovero.enjoytrip.plan.model.UserScheduleDto;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/plan")
@Api("여행계획 컨트롤러  API V1")
public class PlanController {
	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);
	
	private IPlanService planService;
	
	@Autowired
	public PlanController(IPlanService planService) {
		this.planService = planService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PlanDto>> plan() throws Exception {
		logger.info("GET plan called");
		List<PlanDto> plans = planService.findAllPlans();
		return new ResponseEntity<List<PlanDto>>(plans, HttpStatus.OK);
	}
	
	@GetMapping("/getmyplan/{user_id}")
	public List<UserScheduleDto> getmyplan(@PathVariable String user_id) throws Exception {
		logger.info("GET getmyplan called");
		 
//		MembersDto mdto = (MembersDto) session.getAttribute("login");
//		String user_id = mdto.getUser_id();
		
		List<UserScheduleDto> schedules = planService.getMyPlan(user_id);
		
		return schedules;
	}
	
	@GetMapping("/detail/{schedule_id}")
	public UserScheduleDto detail(@PathVariable String schedule_id) throws Exception {
		logger.debug("GET detail called");
		
		int id = Integer.parseInt(schedule_id);
		
		UserScheduleDto schedule = planService.getDetail(id);
		
		return schedule;
	}
}













