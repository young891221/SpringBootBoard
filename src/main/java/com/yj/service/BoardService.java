package com.yj.service;

import java.util.List;
import java.util.Map;

import com.yj.domain.Board;

public interface BoardService {
	
	int selectCountBoard();
	
	boolean insertBoardForm(Board board, String email);
	
	List<Board> selectBoardList(Map<String, Object> map);
	
	Board selectOneBoard(int bId);
	
	String updateBoardForm(Board board, String email);

	boolean deleteBoardForm(int board_id);
	
	void updateViewCount(int bId);
	
	int selectOneGetTotalCount();
	
}
