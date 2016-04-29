package com.yj.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity // JPA임을 표시
@Table(name = "member")
@DynamicUpdate(true)
public class Member implements Serializable{
	
	private static final long serialVersionUID = -521108561135879292L;
	
	public Member() {
		super();
	}

	public Member(int member_id, String member_name, String member_email, String member_image, String userID) {
		super();
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_email = member_email;
		this.member_image = member_image;
		this.userId = userID;
	}
	

	@Id
	@Column(name = "member_id", updatable=false)
	private int member_id;
	
	@Column(name = "member_name")
	private String member_name;
	
	@Column(name = "member_email")
	private String member_email;
	
	@Column(name = "member_image")
	private String member_image;
	
	@Column(name = "member_authority")
	private String authorities;
	
	@Column(name = "userId")
	private String userId;
	

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_image() {
		return member_image;
	}

	public void setMember_image(String member_image) {
		this.member_image = member_image;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
