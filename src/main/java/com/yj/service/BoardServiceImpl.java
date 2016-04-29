package com.yj.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yj.domain.Board;
import com.yj.domain.Member;
import com.yj.repository.BoardRepository;
import com.yj.repository.MemberRepository;


@Service
@Transactional // 해당 클래스에 속한 각 메소드를 다른 클래스에서 호출하면 DB 트랜잭션이 자동(키밋, 롤백)
public class BoardServiceImpl implements BoardService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	public BoardServiceImpl(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	

	@Override
	public int selectCountBoard() {
		
		Object result = boardRepository.selectCountBoard();
		int board_id = 1;
		
		/*
		 * board_id에 대한 초기값이 없을 경우 지정, 있을 경우 1씩 증가시켜주는 구문
		 */
		if (result != null) {
			board_id = (int) result+1;
		}
		
		return board_id;
	}
	
	@Override
	public boolean insertBoardForm(Board board, String email) {
		
		int countBoard_id = selectCountBoard();
		Member member = memberRepository.selectMemberByEmail(email);
		try {
			board.setMember_id(member);
			board.setBoard_id(countBoard_id);
			board.setBoard_view_count(0);
			board.setBoard_insert_date(new Date());
			board.setMember_pw(passwordEncoder.encode(board.getMember_pw()));// BCrpt암호화 방식
		} catch (NullPointerException e) {
			log.debug("insertBoardForm has error: "+e.getMessage());
		}
		
		
		return boardRepository.insertBoardForm(board);
	}
	
	@Override
	public List<Board> selectBoardList(Map<String, Object> map) {
		
		int currentPage = Integer.parseInt(String.valueOf(map.get("currentPage")));
		int pageSize = Integer.parseInt(String.valueOf(map.get("pageSize")));
		String option = String.valueOf(map.get("selectOption"));
		String content = String.valueOf(map.get("selectContent"));
		
		return boardRepository.selectBoardList(currentPage, pageSize, option, content);
	}

	@Override
	public Board selectOneBoard(int board_id) {

		return boardRepository.selectOneBoard(board_id);
	}

	@Override
	public String updateBoardForm(Board board, String email) {

		Member member = memberRepository.selectMemberByEmail(email);
		board.setMember_id(member);
		board.setBoard_update_date(new Date());
		
		return boardRepository.updateBoardForm(board); 
	}

	@Override
	public boolean deleteBoardForm(int board_id) {
		
		return boardRepository.deleteBoardForm(board_id);
	}

	@Override
	public void updateViewCount(int board_id) {
		
		boardRepository.updateViewCount(board_id);
		
	}
	
	@Override
	public int selectOneGetTotalCount() {
		
		int totalCount = boardRepository.selectOneGetTotalCount().intValue();
		return totalCount;
	}
	
}
