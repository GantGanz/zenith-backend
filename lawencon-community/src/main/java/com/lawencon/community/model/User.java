package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "t_user", uniqueConstraints = { @UniqueConstraint(name = "t_user_bk", columnNames = { "email" }),
		@UniqueConstraint(name = "t_user_ck", columnNames = { "email", "fullname" }) })
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

	private static final long serialVersionUID = 6970733509758527448L;

	@Column(name = "fullname", nullable = false, length = 50)
	private String fullname;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "pass", nullable = false)
	private String pass;

	@Column(name = "company", nullable = false, length = 50)
	private String company;

	@Column(name = "is_premium", nullable = false)
	private Boolean isPremium;

	@OneToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@OneToOne
	@JoinColumn(name = "industry_id", nullable = false)
	private Industry industry;

	@OneToOne
	@JoinColumn(name = "position_id", nullable = false)
	private Position position;

	@OneToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

}
