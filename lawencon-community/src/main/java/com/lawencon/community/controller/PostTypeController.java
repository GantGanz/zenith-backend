package com.lawencon.community.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.constant.RoleStaticConstant;
import com.lawencon.community.dto.posttype.PostTypeRes;
import com.lawencon.community.dto.posttype.PostTypesRes;
import com.lawencon.community.service.PostTypeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("post-types")
public class PostTypeController {
	@Autowired
	private PostTypeService postTypeService;

	@GetMapping
	@PreAuthorize("hasAuthority('"+ RoleStaticConstant.MMB +"')")
	public ResponseEntity<PostTypesRes> getAll() {
		final PostTypesRes res = postTypeService.getAll();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('"+ RoleStaticConstant.MMB +"')")
	public ResponseEntity<PostTypeRes> getById(@PathVariable("id") final String id) {
		final PostTypeRes result = postTypeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("code/{code}")
	@PreAuthorize("hasAuthority('"+ RoleStaticConstant.MMB +"')")
	public ResponseEntity<Map<String, Object>> getIdByCode(@PathVariable("code") final String code) {
		final String result = postTypeService.getPostTypeIdByCode(code);
		final Map<String, Object> id = new HashMap<>();
		id.put("id", result);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
}
