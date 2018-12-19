package com.chameleon;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

	@Autowired
	MockMapper mockMapper;

	public Map<String, Object> querySimilarRequest(List<String> requestUriParams, String requestMethod) {
		List<Map<String, Object>> similarRequests = mockMapper.selectSimilar();
		int requestUriParamCount = requestUriParams.size();
		Map<String, Object> request = null;
		for (int i = 0; i < similarRequests.size(); i++) {
			// 匹配请求Method
			if (similarRequests.get(i).get("method").equals(requestMethod)) {
				int index = 1;
				// 匹配请求路径
				while (index <= 10) {
					// 全部命中参数uri，退出匹配
					if (index > requestUriParamCount) {
						if (similarRequests.get(i).get("path_var" + index).equals("")) {
							request = similarRequests.get(i);
						} 
						break;
					}
					if (similarRequests.get(i).get("path_var" + index).equals("")) {
						break;
					}
					String similarPathVar = similarRequests.get(i).get("path_var" + index).toString();
					String requestUriParam = requestUriParams.get(index-1);
					if (!similarPathVar.equals(requestUriParam) ) {
						if (!similarPathVar.equals("*")) {
							break;
						}else {
							
						}
					}
					index++;
				}
			}
			// 找到Request，退出查找
			if (request != null) {
				break;
			}
		}
		return request;
	}

	public Map<String, Object> queryAccurateRequest(List<String> requestUriParams, String requestMethod) {
		String pathVarsSql = "";
		for (int i = 1; i < 7; i++) {
			if (requestUriParams.size() >= i) {
				pathVarsSql += " path_var" + i + "='" + requestUriParams.get(i - 1) + "' ";
			} else {
				pathVarsSql += " path_var" + i + " ='' ";
			}

			if (i != 6) {
				pathVarsSql += " and ";
			}
		}
		List<Map<String, Object>> mockRequests = mockMapper.selectRequest(requestMethod, pathVarsSql);
		if (mockRequests.size() == 1) {
			return mockRequests.get(0);
		} else if (mockRequests.size() > 1) {
			String requestids = new String();
			for (int i = 0; i < mockRequests.size(); i++) {
				requestids += mockRequests.get(i).get("id") + ";";
			}
			String msg = "Resource is not unique, request id :" + requestids;
			throw new RuntimeException(msg);
		} else {
			return null;
		}
	}

	public Map<String, Object> queryRequest(List<String> requestUriParams, String requestMethod) {
		Map<String, Object> mockRequest = queryAccurateRequest(requestUriParams, requestMethod);
		if (mockRequest == null) {
			mockRequest = querySimilarRequest(requestUriParams, requestMethod);
		}
		return mockRequest;
	}

}
