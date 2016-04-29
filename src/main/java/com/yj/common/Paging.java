package com.yj.common;


public class Paging {

	/**
	 * @author Kim YJ
	 * @param pageSize - 한 페이지의 글의 개수
	 * @param currentPage - 현재 페이지
	 * @param startRow - 한 페이지의 시작글 번호 ex)pageSize가 5인경우 1페이지 - 1 // 2페이지 - 6
	 * @param endRow - 한 페이지의 마지막 글번호 ex) pageSize가 5인경우 1페이지 - 5 // 2페이지 - 10
	 * @param totalRow - 전체 글의 갯수
	 * @param pageCount - 전체 페이지수
	 * @param remainRow - 남은 행이 있는지 검사, 전체 행(=COUNT) % 페이지 크기(10)0이면 딱 맞는 것이고 1이면 false 적거나 많은 것
	 * @param pageBlock - 하나의 블록에 몇 페이지가 속해있는지(즉, 3이면 보이지는 곳에는 1,2,3 페이지가 한블록 // 4,5,6 페이지가 한 블록)
	 * @param result - 시작페이지를 잡아준다
	 * @param startPage - 현재 블록의 시작 페이지 아래를 보면 6페이지부터 2블록이 된다
	 * @param endPage - 현재 블록의 마지막 페이지 
	 * @param number - 실제 화면에 보여지는 시작 글 번호 1페이지 맨위 번호 
	 */
	PagingObject paging = new PagingObject();
	int pageSize;
	int currentPage;
	int startRow;
	int endRow;
	int totalRow;
	int pageCount;
	int remainRow;
	int pageBlock;
	int result;
	int startPage;
	int endPage;
	int number;

	
	public PagingObject setPagingInfo(int totalRow, int pageSize, int currentPage) {
		this.totalRow = totalRow;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		initialize();

		return paging;
	}

	public void initialize() {

		if (totalRow % pageSize == 0) {
			remainRow = 0;
		} else {
			remainRow = 1;
		}

		pageCount = totalRow / pageSize + remainRow;
		pageBlock = 5;		
		result = (currentPage - 1) / pageBlock;	
		startPage = result * pageBlock + 1;
		endPage = startPage + pageBlock - 1;
		startRow = (currentPage - 1) * pageSize + 1;
		endRow = currentPage * pageSize;
		
		/*
		 * 남는 페이지에 대한 처리를 위해서 사용
		 * 만약에 내가 페이지 블록으로 1~5까지 설정했을 때, endPage 즉 3,4,5에 내용이 없어도 페이지는 생성하게 된다.
		 * 즉 endPage = pageCount로 잡아주어 해당 페이지까지만 생성해주는 것이다.
		 */
		if (endPage > pageCount){ 
			endPage = pageCount;
		}
		
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setPageBlock(pageBlock);
		paging.setPageCount(pageCount);
		paging.setEndRow(endRow);
		paging.setStartRow(startRow);
		paging.setCurrentPage(currentPage);
	}

}