package com.yj.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.yj.controller.BoardController;

@Aspect
public class AspectTest {
	private final Log log = LogFactory.getLog(BoardController.class);
	
	@Pointcut("@annotation(com.yj.aop.AspectAnnotation)")
	public void updateTest() {}
	
	@After("updateTest()")
	public void afterTest() {
		log.info("after acpect!!");
	}
	
}
