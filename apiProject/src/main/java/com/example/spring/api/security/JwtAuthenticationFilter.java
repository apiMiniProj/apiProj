package com.example.spring.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {

	private JwtTokenProvider jwtTokenProvider;

	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		String token = jwtTokenProvider.resolveToken(req);
		String requestURI = req.getRequestURI();
		
		logger.info("JwtAuthenticationFilter.doFilter chk token : " + token);
		if (token != null && token.startsWith("Bearer ")) {   // token 검증
			token = token.substring(7);
			
			if(jwtTokenProvider.validateToken(token)) {
				Authentication auth = jwtTokenProvider.getAuthentication(token);    // 인증 객체 생성
				SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 인증 객체 저장
	
				logger.info("JwtAuthenticationFilter.doFilter auth : " + auth.toString());
			}else {
				logger.info("not found token info!!  uri : "+ requestURI);
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}