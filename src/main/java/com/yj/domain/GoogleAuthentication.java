package com.yj.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GoogleAuthentication implements Authentication{
	
	private static final long serialVersionUID = -521108561135879292L;
	private final Collection<GrantedAuthority> authorities;
	private boolean authenticated = false;
	
	public GoogleAuthentication(Collection<? extends GrantedAuthority> authorities) {
		if (authorities == null) {
			this.authorities = AuthorityUtils.NO_AUTHORITIES;
			return;
		}

		for (GrantedAuthority a : authorities) {
			if (a == null) {
				throw new IllegalArgumentException(
						"Authorities collection cannot contain any null elements");
			}
		}
		ArrayList<GrantedAuthority> temp = new ArrayList<GrantedAuthority>(
				authorities.size());
		temp.addAll(authorities);
		this.authorities = Collections.unmodifiableList(temp);
	}
	
	@Override
	public String getName() {
		
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    UserDetails userDetails = null;
	    if (principal instanceof UserDetails) {
	      userDetails = (UserDetails) principal;
	    }
	    return userDetails;
	}

	@Override
	public boolean isAuthenticated() {

		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
}
