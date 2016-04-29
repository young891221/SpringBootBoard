package com.yj.repository;

import java.util.List;

import com.yj.domain.Board;

public interface BoardRepository {
	
	Object selectCountBoard();
	
	boolean insertBoardForm(Board board);
	
	List<Board> selectBoardList(int currentPage, int pageSize, String option, String content);
	
	Board selectOneBoard(int bId);
	
	String updateBoardForm(Board board);
	
	boolean deleteBoardForm(int board_id);
	
	void updateViewCount(int board_id);
	
	Long selectOneGetTotalCount();
	
}
