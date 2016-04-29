package com.yj.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.CssLinkResourceTransformer;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yj.helper.ResourceHelper;
import com.yj.logger.LoggerInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.yj.config" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ResourceHelper resourceHelper() {

		return new ResourceHelper();
	}

	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver(ResourceUrlProvider urlProvider) {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		Map<String, Object> map = new HashMap<>();
		map.put("helper", resourceHelper());
		resolver.setAttributesMap(map);
		resolver.setContentType("text/html;charset=utf-8");
		resolver.setPrefix("/board/");
		resolver.setSuffix(".ftl");

		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		ResourceResolver resolver = new VersionResourceResolver().addContentVersionStrategy("/**");

		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/").setCachePeriod(86400) // 캐시
				.resourceChain(true) // 체인 결정
				.addResolver(resolver).addTransformer(new CssLinkResourceTransformer());
	}

	// db연동관련
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		return objectMapper;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/resources/**");
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);

		return characterEncodingFilter;
	}
	
	

}
