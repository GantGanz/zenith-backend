package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.like.LikeInsertReq;
import com.lawencon.community.dto.like.LikeRes;
import com.lawencon.community.dto.like.LikeUpdateReq;
import com.lawencon.community.dto.like.LikesRes;
import com.lawencon.community.service.LikeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("likes")
public class LikeController {
	@Autowired
	private LikeService likeService;

	@GetMapping("count-like/{id}")
	public ResponseEntity<Long> countLike(@PathVariable("id") final String postId) {
		final Long res = likeService.countLike(postId);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
		
	@GetMapping
	public ResponseEntity<LikesRes> getAllByUserId() {
		final LikesRes res = likeService.getAllByUserId();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody final LikeInsertReq data) {
		final InsertRes res = likeService.insert(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("soft-delete")
	public ResponseEntity<UpdateRes> softDelete(@RequestBody final LikeUpdateReq data) {
		final UpdateRes res = likeService.softDelete(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<LikeRes> getById(@PathVariable("id") final String id) {
		final LikeRes result = likeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}