package com.uxpsystems.assignment.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingFilter extends ZuulFilter {
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("request -> {} request uri-> {} method-> {}", request, request.getRequestURI(), request.getMethod());
		return null;
	}

	@Override
	public String filterType() {
		return "pre"; 
	}

	@Override
	public int filterOrder() {
		return 0;
	}
}