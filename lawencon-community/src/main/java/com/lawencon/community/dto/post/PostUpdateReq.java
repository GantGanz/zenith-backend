package com.lawencon.community.dto.post;

import lombok.Data;

@Data
public class PostUpdateReq {

	private String id;
	private String postTitle;
	private String postContent;
	private Boolean isActive;
	private Integer version;
}
