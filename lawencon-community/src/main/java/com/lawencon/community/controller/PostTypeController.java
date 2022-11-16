package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.posttype.PostTypesRes;
import com.lawencon.community.service.PostTypeService;

@RestController
@RequestMapping("post-types")
public class PostTypeController {
	@Autowired
	private PostTypeService postTypeService;

	@GetMapping
	public ResponseEntity<PostTypesRes> getAll() {
		final PostTypesRes res = postTypeService.getAll();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}