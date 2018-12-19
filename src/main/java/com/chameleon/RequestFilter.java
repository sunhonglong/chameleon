package com.chameleon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@WebFilter("/*")
@Component
public class RequestFilter implements Filter {

	@Autowired
	RequestService requestService;
	@Autowired
	MockMapper mockMapper;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String requestMethod = request.getMethod();
		if (requestMethod.equals("OPTIONS")) {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		String uri = request.getRequestURI();
		if (uri.substring(uri.length() - 1, uri.length()).endsWith("/")) {
			uri = uri.substring(0, uri.length() - 1);
		}
		if (uri.substring(0, 1).endsWith("/")) {
			uri = uri.substring(1, uri.length());
		}
		String[] uriVars = uri.split("/");
		List<String> requestUriParams = new ArrayList<>();
		for (int i = 0; i < uriVars.length; i++) {
			requestUriParams.add(uriVars[i]);
		}
		Map<String, Object> mockRequest = requestService.queryRequest(requestUriParams, requestMethod);
		if (mockRequest == null) {
			response.setStatus(404);
			response.getWriter().write("Not found resource");
		} else {
			List<Map<String, Object>> mockHeaders = mockMapper.selectHeaderByRequest(mockRequest.get("id").toString());
			for (int i = 0; i < mockHeaders.size(); i++) {
				String headerKey = mockHeaders.get(i).get("header_key").toString();
				String headerValue = mockHeaders.get(i).get("header_value").toString();
				response.setHeader(headerKey.trim(), headerValue.trim());
			}
			String responseContent;
			if (!mockRequest.get("response").equals("")) {
				responseContent = mockRequest.get("response").toString();
			}else if (mockRequest.get("response")!=null) {
				responseContent = mockRequest.get("response_text").toString();
			}else {
				responseContent = "Nothing!";
			}
			response.getWriter().write(responseContent);
			return;
		}
//		filterChain.doFilter(servletRequest,servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
