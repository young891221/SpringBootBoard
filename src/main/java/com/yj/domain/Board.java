package com.yj.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "board")
public class Board implements Serializable{
	// 객체를 파일에 쓰거나 전송하기 위해 직렬화를 하는데 그러기 위해 Serializable 인터페이스 구현 
	private static final long serialVersionUID = -521108561135879292L;
	//serialVersionUID는 직렬화에 사용되는 고유 아이디
	
	public Board(Member member_id, String member_pw) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
	}
	
	public Board() {
		super();
	}

	/*
	 * @GeneratedValue는 주키의 값을 위한 자동 생성 전략을 명시
	 * AUTO는 특정 DB에 맞게 자동 선택
	 */
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "board_id", updatable=false)
	private int board_id;
	
	@Column(name = "board_group")
	private String board_group;
	
	@Column(name = "board_title")
	private String board_title;
	
	@Column(name = "board_sub_title")
	private String board_sub_title;
	
	@Column(name = "board_image")
	private String board_image;
	
	@Column(name = "board_content")
	private String board_content;
	
	@Column(name = "board_view_count")
	private int board_view_count;
	
	@Column(name = "board_status")
	private int board_status;
	
	@Column(name = "board_insert_date", updatable=false)
	private Date board_insert_date;
	
	@Column(name = "board_update_date", insertable=false)
	private Date board_update_date;
	
	@Column(name = "board_delete_date", insertable=false)
	private Date board_delete_date;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member_id;
	
	@Column(name = "member_pw", updatable=false)
	private String member_pw;
	
	

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoard_group() {
		return board_group;
	}

	public void setBoard_group(String board_group) {
		this.board_group = board_group;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_sub_title() {
		return board_sub_title;
	}

	public void setBoard_sub_title(String board_sub_title) {
		this.board_sub_title = board_sub_title;
	}

	public String getBoard_image() {
		return board_image;
	}

	public void setBoard_image(String board_image) {
		this.board_image = board_image;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getBoard_view_count() {
		return board_view_count;
	}

	public void setBoard_view_count(int board_view_count) {
		this.board_view_count = board_view_count;
	}

	public int getBoard_status() {
		return board_status;
	}

	public void setBoard_status(int board_status) {
		this.board_status = board_status;
	}

	public Date getBoard_insert_date() {
		return board_insert_date;
	}

	public void setBoard_insert_date(Date board_insert_date) {
		this.board_insert_date = board_insert_date;
	}

	public Date getBoard_update_date() {
		return board_update_date;
	}

	public void setBoard_update_date(Date board_update_date) {
		this.board_update_date = board_update_date;
	}

	public Date getBoard_delete_date() {
		return board_delete_date;
	}

	public void setBoard_delete_date(Date board_delete_date) {
		this.board_delete_date = board_delete_date;
	}

	public Member getMember_id() {
		return member_id;
	}

	public void setMember_id(Member member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	
	
}
