package com.lawencon.community.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfig {

	@Bean
	public String[] getAllowedOrigins() {
		final String[] allowedOrigins = new String[] {"http://localhost:4200","http://192.168.10.229:4200","http://192.168.10.186:4200","http://192.168.10.75:4200"};
		return allowedOrigins;
	}

	@Bean
	@Qualifier("webIgnoring")
	public List<RequestMatcher> matchers() {
		final List<RequestMatcher> matchers = new ArrayList<>();
		matchers.add(new AntPathRequestMatcher("/login/**", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/users/register", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/user-verifications/**", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/positions/active", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/industries/active", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/v3/**", HttpMethod.GET.name()));
		return matchers;
	}
}
