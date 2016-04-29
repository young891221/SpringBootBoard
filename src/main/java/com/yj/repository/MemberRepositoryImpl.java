package com.yj.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yj.domain.Member;

@Repository
public class MemberRepositoryImpl implements MemberRepository{
	private final Log log = LogFactory.getLog(MemberRepositoryImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public void insertMember(Member member) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(member);
		} catch (HibernateException e) {
			log.debug("insertMember failed: "+e.toString());
		}
		
	}
	
	@Override
	public int selectCountMember() {
		
		int member_id = 1;
		Object nullCheckId = null;
		
		try {
			nullCheckId = sessionFactory.getCurrentSession().createCriteria(Member.class)
					.setProjection(Projections.max("member_id")).uniqueResult();

			if (nullCheckId != null) {
				member_id = Integer.parseInt(String.valueOf(nullCheckId)) + 1;
			}

		} catch (HibernateException e) {
			log.debug("selectCountMember failed: "+e.toString());
		}
		
		return member_id;
	}
	
	@Override
	public boolean selectEmailMember(String email) {
		
		boolean checkEmail = true;
		try {
			Object result = sessionFactory.getCurrentSession().createCriteria(Member.class)
			.add(Restrictions.eq("member_email", email)).uniqueResult();
			if(result != null) {
				checkEmail = false;
			}
		} catch (HibernateException e) {
			log.debug("selectEmailMember failed: "+e.toString());
		}
		
		return checkEmail;
	}

	@Override
	public Member selectEmail(String userId) {
		Member member = new Member();
		try {
			member = (Member) sessionFactory.getCurrentSession().createCriteria(Member.class)
			.add(Restrictions.eq("userId", userId)).uniqueResult();
		} catch (HibernateException e) {
			log.debug("selectEmail failed: "+e.toString());
		}
		
		return member;
	}

	@Override
	public Member selectMemberByEmail(String email) {
		Member member = new Member();
		try {
			member = (Member) sessionFactory.getCurrentSession().createCriteria(Member.class)
			.add(Restrictions.eq("member_email", email)).uniqueResult();
		} catch (HibernateException e) {
			log.debug("selectEmail failed: "+e.toString());
		}
		
		return member;
	}

}
