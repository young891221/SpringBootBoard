package com.yj.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yj.aop.AspectAnnotation;
import com.yj.common.Paging;
import com.yj.common.PagingObject;
import com.yj.domain.Board;
import com.yj.service.BoardService;

@RestController
public class BoardRestController {
	private final Log log = LogFactory.getLog(BoardController.class);
	private final String DIRECTORY = "C:\\Users\\Kim YJ\\Documents\\workspace(pilot)\\pilot-yjinyj\\src\\main\\resources\\static\\imageFile";
	private final String IMAGEPATH = "/resources/imageFile";
	private final BCryptPasswordEncoder passwordEncoder;

	/*
	 * BCryptPassword 인코딩을 위한 주입
	 */
	@Inject
	public BoardRestController(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/insertBoardForm", method = RequestMethod.POST)
	@AspectAnnotation
	public String insertBoardForm(@RequestBody Board board, HttpSession session) {
		
		String resultMessage = "게시판 생성에 실패하였습니다.";

		boolean result = boardService.insertBoardForm(board,(String)session.getAttribute("member_email"));
		if (result) {
			resultMessage = "게시판 생성 성공!";
		}

		return resultMessage;
	}

	@RequestMapping(value = "/selectOptionBoard", method = RequestMethod.POST)
	public Map<String, Object> selectOptionBoard(@RequestBody Map<String, Object> map,
			@RequestParam(required = false, defaultValue = "1") int page) {

		List<Board> selectOptionBoardList = new ArrayList<Board>();

		int totalRow = boardService.selectOneGetTotalCount();
		int pageSize = 10;
		Paging paging = new Paging();
		PagingObject pagingVO = paging.setPagingInfo(totalRow, pageSize, page);

		if (totalRow > 0) {
			Map<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("pageSize", pageSize);
			pageMap.put("currentPage", page);
			pageMap.put("selectOption", map.get("selectOption"));
			pageMap.put("selectContent", map.get("selectContent"));
			selectOptionBoardList = boardService.selectBoardList(pageMap);
		}

		map.put("selectOptionBoardList", selectOptionBoardList);
		map.put("pagingVO", pagingVO);

		return map;
	}

	@RequestMapping(value = "/updateBoardForm", method = RequestMethod.POST)
	public String updateBoardForm(@RequestBody Board board, HttpSession session, Authentication authentication) {
		String result = "수정을 실패하였습니다.";
		//session 관리를 authentication으로 수정하여 인증 관리하기
		//Details details = (Details)authentication.getDetails();
		result = boardService.updateBoardForm(board, (String) session.getAttribute("member_email"));

		return result;
	}

	@RequestMapping(value = "/deleteBoardForm", method = RequestMethod.GET)
	public String deleteBoardForm(@RequestParam int board_id) {
		String result = "삭제를 실패하였습니다.";

		if (boardService.deleteBoardForm(board_id)) {
			result = "삭제에 성공하였습니다!";
		} else {
			log.debug("deleteBoardForm: " + board_id);
		}

		return result;
	}

	@RequestMapping(value = "/memberCheck", method = RequestMethod.GET)
	public boolean memberCheck(@RequestParam int board_id, @RequestParam String member_email,
			@RequestParam String member_pw) {

		Board memberCheck = new Board();
		boolean check = false;

		memberCheck = boardService.selectOneBoard(board_id);
		if (memberCheck.getMember_id().getMember_email().equals(member_email)
				&& passwordEncoder.matches(member_pw, memberCheck.getMember_pw())) {
			check = true;
		}
		
		return check;
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public Map<String, Object> uploadImage(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		BufferedOutputStream stream = null;
		try {
			String filename = uploadFile.getOriginalFilename();
			String filepath = Paths.get(DIRECTORY, filename).toString();
			String resultPath = Paths.get(IMAGEPATH, filename).toString();
			// Save the file locally
			stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadFile.getBytes());

			map.put("resultPath", resultPath);
			map.put("filename", filename);

		} catch (Exception e) {
			log.debug("uploadImage failed" + e.toString());
		} finally {
			stream.close();
		}

		return map;
	}

}
