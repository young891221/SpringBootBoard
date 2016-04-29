package com.yj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yj.common.Paging;
import com.yj.common.PagingObject;
import com.yj.domain.Board;
import com.yj.service.BoardService;


@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/")
	public String home() {
		return "redirect:/boardList";
	}

	@RequestMapping("/boardForm")
	public void insertForm() {
	}

	@RequestMapping("/boardError")
	public void boardError() {
	}

	@RequestMapping(value = "/boardFormPop", method = RequestMethod.GET)
	public Model boardFormPop(@RequestParam(required = false) String bImage, Model model) {

		if (bImage != null)
			model.addAttribute(bImage);

		return model;
	}

	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public Model listBoardForm(@RequestParam(required = false, defaultValue = "1") int page, Model model,
			HttpSession session, Authentication authentication) {
		List<Board> selectBoardList = new ArrayList<Board>();

		// 세션값(이메일) 설정
		// Security로 세션 관리하는 방법 찾아보기
		session.setAttribute("member_email", authentication.getName());

		int totalRow = boardService.selectOneGetTotalCount();
		int pageSize = 10;
		Paging paging = new Paging();
		PagingObject pagingVO = paging.setPagingInfo(totalRow, pageSize, page);

		if (totalRow > 0) {
			Map<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("pageSize", pageSize);
			pageMap.put("currentPage", page);
			selectBoardList = boardService.selectBoardList(pageMap);
		}

		model.addAttribute("selectBoardList", selectBoardList);
		model.addAttribute("pagingVO", pagingVO);

		return model;
	}

	@RequestMapping(value = "/selectOneBoard", method = RequestMethod.GET)
	public String selectOneBoard(@RequestParam int board_id, Model model) {

		Board selectOneBoard = new Board();

		selectOneBoard = boardService.selectOneBoard(board_id);
		boardService.updateViewCount(board_id); // 뷰 카운트 증가 함수

		model.addAttribute("selectOneBoard", selectOneBoard);

		return "/boardView";
	}

}
