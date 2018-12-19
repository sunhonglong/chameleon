package com.chameleon;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MockMapper {
	
	@Select("select * from mock_request where method = #{requestMethod} and ${pathVarsSql}  ")
	List<Map<String, Object>> selectRequest(@Param("requestMethod")String requestMethod,@Param("pathVarsSql")String pathVarsSql);
	
	@Select("select * from mock_response_header where request = #{requestId}  ")
	List<Map<String, Object>> selectHeaderByRequest(@Param("requestId")String requestId);
	
	@Select("select * from mock_request where path_var1 = '*' or path_var2 = '*' or path_var3 = '*' or path_var4 = '*' or path_var5 = '*' or path_var6 = '*'")
	List<Map<String, Object>> selectSimilar();
	
}
