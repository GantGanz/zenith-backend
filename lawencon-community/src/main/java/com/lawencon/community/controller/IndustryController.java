package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.industry.IndustriesRes;
import com.lawencon.community.dto.industry.IndustryInsertReq;
import com.lawencon.community.service.IndustryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("industries")
public class IndustryController {
	@Autowired
	private IndustryService industryService;

	@GetMapping
	public ResponseEntity<IndustriesRes> getAll() {
		final IndustriesRes res = industryService.getAll();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody final IndustryInsertReq data) {
		final InsertRes res = industryService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
