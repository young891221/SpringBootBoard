package com.yj.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.domain.Member;
import com.yj.repository.MemberRepository;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	//private final Log log = LogFactory.getLog(BoardRepositoryImpl.class);
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public void insertMember(Member member) {
		
		memberRepository.insertMember(member);
	}
	
	@Override
	public int selectCountMember() {
		
		return memberRepository.selectCountMember();
	}
	
	@Override
	public boolean selectEmailMember(String email) {
		
		return memberRepository.selectEmailMember(email);
	}

	@Override
	public Member selectEmail(String userId) {
		
		return memberRepository.selectEmail(userId);
	}
}
