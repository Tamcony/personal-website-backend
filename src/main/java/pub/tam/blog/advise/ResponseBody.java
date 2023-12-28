package pub.tam.blog.advise;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pub.tam.blog.annotion.NoWrapper;
import pub.tam.blog.model.HttpResponse;

@RestControllerAdvice(basePackages = "pub.tam.blog.controller")
public class ResponseBody implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if(returnType.getDeclaringClass().isAnnotationPresent(NoWrapper.class)){
            return false;//不拦截
        }
        return true;//所有都拦截
    }
    @ExceptionHandler(Exception.class)
    public HttpResponse exceptionHandler(Exception e){
        HttpResponse<Object> httpResponse = new HttpResponse<>();
        httpResponse.setCode(1);
        httpResponse.setMessage(e.getMessage());
        return httpResponse;
    }
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpResponse<Object> httpResponse = new HttpResponse<>();
        httpResponse.setCode(0);
        //初始message转换为空
        String message = StrUtil.EMPTY;
        httpResponse.setMessage(message);
        if (body instanceof Page) {
            // 如果 body 是 Page 类型，则进行特殊处理
            Page<?> pageBody = (Page<?>) body;
            // 构建新的返回结构
            Object newData = JSONUtil.createObj()
                    .set("total", pageBody.getTotalElements())
                    .set("items", pageBody.getContent());
            httpResponse.setData(newData);
            httpResponse.setData(newData);
        }else{
            httpResponse.setData(body);
        }
        if(selectedConverterType.equals(StringHttpMessageConverter.class)){
            return JSONUtil.toJsonStr(httpResponse);
        }else{
            return httpResponse;
        }
    }
}
