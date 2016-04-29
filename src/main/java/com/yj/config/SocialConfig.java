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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;

import com.yj.google.PostToWallAfterConnectInterceptor;
import com.yj.google.SimpleSignInAdapter;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

	@Inject
	private DataSource dataSource;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		cfConfig.addConnectionFactory(
				new GoogleConnectionFactory(env.getProperty("google.appKey"), env.getProperty("google.appSecret")));
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new UserIdSource() {
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException("ConnectionRepository 사용불가: no user signed in");
				}
				return authentication.getName();
			}
		};
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
	}

	// API Binding Beans
	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
		return connectController;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Google googleConnection(ConnectionRepository repository) {
		Connection<Google> connection = repository.findPrimaryConnection(Google.class);
		return connection != null ? connection.getApi() : null;
	}

	// Web Controller and Filter Beans
	@Bean
	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator,
			UsersConnectionRepository usersConnectionRepository) {
		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository,
				new SimpleSignInAdapter(new HttpSessionRequestCache()));
	}

	@Bean
	public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository,
			UserIdSource userIdSource) {
		return new ReconnectFilter(usersConnectionRepository, userIdSource);
	}


}
