//package com.uxpsystems.assignment.zuul.filter;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.uxpsystems.assignment.kafka.producer.Producer;
//
//import lombok.extern.slf4j.Slf4j;
//
////@Component
//@Slf4j
//public class EventFilter extends ZuulFilter {
//
//	private static final String REQUEST_PATH = "profile";
//	private static final String HTTP_PUT_METHOD = "PUT";
//	private static final String HTTP_DELETE_METHOD = "DELETE";
//	private static final String HTTP_GET_METHOD = "GET";
//
//	@Autowired
//	private Producer producer;
//
//	@Override
//	public boolean shouldFilter() {
//		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
//		String method = request.getMethod();
//		String requestURI = request.getRequestURI();
//		return (HTTP_GET_METHOD.equalsIgnoreCase(method) || HTTP_PUT_METHOD.equalsIgnoreCase(method)
//				|| HTTP_DELETE_METHOD.equalsIgnoreCase(method)) && requestURI.endsWith(REQUEST_PATH);
//	}
//
//	@Override
//	public Object run() throws ZuulException {
//		log.info("request -> Inside PreFilter get request");
//		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
//		RequestContext context = RequestContext.getCurrentContext();
//		String method = request.getMethod();
//		if(HTTP_PUT_METHOD.equalsIgnoreCase(method)) {
//			producer.sendMessage(request.getParameter("message"));
//		}
////		else if(HTTP_GET_METHOD.equalsIgnoreCase(method)) {
////			producer.sendMessage(request.getParameter("message"));
////		}
////		else if(HTTP_GET_METHOD.equalsIgnoreCase(method)) {
////			producer.sendMessage(request.getParameter("message"));
////		}
//		context.setSendZuulResponse(false);
//		return null;
//	}
//
//	@Override
//	public String filterType() {
//		return "pre";
//	}
//
//	@Override
//	public int filterOrder() {
//		return 1;
//	}
//
//}
