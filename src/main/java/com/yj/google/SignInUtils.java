package com.yj.google;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class SignInUtils {
	
	/**
	 * Programmatically signs in the user with the given the user ID.
	 */
	public static void signin(String userId) {
		// 인증 부분 GoogleAuthentication으로 넣기
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));	
	}
	
}
