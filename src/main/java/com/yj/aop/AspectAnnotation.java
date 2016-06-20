package com.yj.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
  * @Project : SpringBootBoard
  * @Date : 2016. 6. 20. 
  * @author : Kim YJ
  * @변경이력 :
  */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AspectAnnotation {
}
