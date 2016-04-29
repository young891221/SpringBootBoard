package com.yj.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yj.domain.Board;

@Repository
public class BoardRepositoryImpl implements BoardRepository {
	private final Log log = LogFactory.getLog(BoardRepositoryImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Object selectCountBoard() {

		Object board_id = null;

		try {
			board_id =  sessionFactory.getCurrentSession().createCriteria(Board.class)
					.setProjection(Projections.max("board_id")).uniqueResult();
		} catch (HibernateException e) {
			log.debug("selectCountBoard failed: "+e.toString());
		}
		return board_id;
	}

	@Override
	public boolean insertBoardForm(Board board) {
		boolean result = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(board);
			result = true;
		} catch (Exception e) {
			log.debug("insertBoardForm failed: "+e.toString());
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> selectBoardList(int currentPage, int pageSize, String option, String content) {

		List<Board> list = new ArrayList<Board>();

		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(Board.class);
			crit.add(Restrictions.isNull("board_delete_date")).setFirstResult((currentPage - 1) * pageSize)
					.setMaxResults(pageSize).addOrder(Order.desc("board_id"));

			if ("board_title".equals(option)) {
				list = crit.add(Restrictions.disjunction().add(Restrictions.like(option, content, MatchMode.ANYWHERE))).list();
			} else if ("board_group".equals(option)) {
				list = crit.add(Restrictions.like(option, content, MatchMode.ANYWHERE)).list();
			} else {
				list = crit.list();
			}

		} catch (HibernateException e) {
			log.debug("selectBoardList failed: "+e.toString());
		}

		return list;
	}

	@Override
	public Board selectOneBoard(int board_id) {

		Board board = new Board();

		try {
			board = (Board) sessionFactory.getCurrentSession().createCriteria(Board.class)
					.add(Restrictions.eq("board_id", board_id)).uniqueResult();
		} catch (HibernateException e) {
			log.debug("selectOneBoard failed: "+e.toString());
		} finally {
			Hibernate.initialize(board);
		}

		return board;
	}

	@Override
	public String updateBoardForm(Board board) {
		String result = "fail";
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(board);
			result = "수정에 성공하였습니다!";
		} catch (HibernateException e) {
			log.debug("updateBoardForm failed: "+e.toString());
		}

		return result;
	}
	
	@Override
	public boolean deleteBoardForm(int board_id) {
		Board board = new Board();
		boolean result = false;
		board = (Board) sessionFactory.getCurrentSession().createCriteria(Board.class)
		.add(Restrictions.eq("board_id", board_id)).uniqueResult();
		
		if(board != null) {
			board.setBoard_delete_date(new Date());
			result = true;
		} else {
			log.debug("deleteBoardForm failed(null): "+board_id);
		}

		return result;
	}

	@Override
	public void updateViewCount(int board_id) {
		try {
			/*
			 * HQL로 쿼리를 실행하여 프록시 서버에서 여러 사용자의 요청을 담아두고 쿼리를 실행하는 것이 가능하다  
			 */
			String hql = "UPDATE board SET board_view_count = board_view_count + 1 WHERE board_id = :id";
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
			query.setParameter("id", board_id);
			query.executeUpdate();
		} catch (HibernateException e) {
			log.debug("updateViewCount Failed: ID = "+board_id);
		}

	}

	@Override
	public Long selectOneGetTotalCount() {
		Long totalCount = null;
		try {
			totalCount = (Long) sessionFactory.getCurrentSession().createCriteria(Board.class)
					.add(Restrictions.isNull("board_delete_date")).setProjection(Projections.rowCount()).uniqueResult();
		} catch (HibernateException e) {
			log.debug("selectOneGetTotalCount Error: "+e.toString());
		}

		return totalCount;
	}


	

}
