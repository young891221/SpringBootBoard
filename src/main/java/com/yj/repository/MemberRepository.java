package com.yj.repository;

import com.yj.domain.Member;

public interface MemberRepository {
	
	void insertMember(Member member);
	
	int selectCountMember();
	
	boolean selectEmailMember(String email);
	
	Member selectEmail(String userId);

	Member selectMemberByEmail(String email);
}
