package com.yj.service;

import com.yj.domain.Member;

public interface MemberService {

	void insertMember(Member member);
	
	int selectCountMember();
	
	boolean selectEmailMember(String email);
	
	Member selectEmail(String userId);
}
