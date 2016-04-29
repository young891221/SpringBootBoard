/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yj.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * 로그인 요청 인증에 대한 추가 설정 리스트
 * 1. 사용자 저장소 설정
 * 2. 인증이 필요한 요청과 아닌 요청을 명시하고, 필요한 권한이 무엇인지 명시
 * 3. 평범한 기본 로그인 화면을 대체하기 위한 수정된 로그인 화면 제공
 * */

@Configuration
@EnableWebSecurity //스프링 시큐리티에 관련된 기본적인 사항들(인증 필터 등)이 설정
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Inject
	private DataSource dataSource;

	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        	.dataSource(dataSource)
        	.passwordEncoder(passwordEncoder());
    }
	
	@Override //webSecurity는 스프링 시큐리티의 필터 연결을 설정하기 위한 오버라이딩
	public void configure(WebSecurity web) throws Exception { //특정 요청에 대해서는 시큐리티 설정을 무시하도록 하는 등 전체에 관한 설정
		web
			.ignoring()
				.antMatchers("/**/*.css", "/**/*.png", "/**/*.gif", "/**/*.jpg", "/**/*.js"); //이 리소스와 관련해 접근시 시큐리티 설정을 무시 
	}

	/**
	 * authenticated()는 애플리케이션에 로그인된 사용자가 요청을 수행할 때 필요하다
	 * 만약 사용자가 인증되지 않았다면, 스프링 시큐리티 필터는 요청을 잡아내고 사용자를 로그인 페이지로 리다이렉션 해준다.
	 * permitAll()은 어떠한 보안 요구 없이 요청을 허용해 준다.
	 */
	
	@Override //httpSecurity는 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩
	protected void configure(HttpSecurity http) throws Exception { //HttpSecurity를 오버라이드해서 인가, 로그인, 로그아웃 설정
		http
			.formLogin()
				.loginPage("/signin")
				.loginProcessingUrl("/signin/authenticate")
				.failureUrl("/signin?param.error=bad_credentials")
				.usernameParameter("username")
			.and()
				.logout()
					.logoutUrl("/signout")
					.logoutSuccessUrl("/signin")
					.deleteCookies("JSESSIONID")
					.invalidateHttpSession(true)
			.and()
				.authorizeRequests()
					.antMatchers("/resources/**", "/signin/**", "/signup/**")
					.permitAll()
					.antMatchers("/**").authenticated() //인증만으로 접근 가능
			.and()
				.rememberMe()
			.and()
				.exceptionHandling().accessDeniedPage("/Access_Denied(403)")
			.and()
				.csrf()
				.disable()
        	.sessionManagement()
	        	.maximumSessions(1)
	        	.expiredUrl("/signin?expired")
	        	.maxSessionsPreventsLogin(true)
	        	.and()
	        	.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) //세션이 필요한 경우에만 사용(default)
	        	.invalidSessionUrl("/signin");

	}
	
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}

}
