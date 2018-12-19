# chameleon
##HTTP RESTfull Mock    
request:Mock的请求
  method：请求方法，POST、GET、PUT、DELETE   
  response：请求响应，如json，html，txt    
  response_text:请求响应，文本或较长内容时使用；请求响应response优先级高于response_text，response为空时（空字符串），将返回response_text内容   
  path_var1-path_var6：请求uri参数，支持通配符*;如：请求http://${domain}/a/b/c ，path_var1=a;path_var2=b;path_var3=c;     

response_header:响应报文header    
  request:request id    
  header_key:header key,如Content-type   
  header_value:header value,如application/json;charset=utf-8   
  
