package com.commerce.common.config;

import com.commerce.common.vo.RestResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
public class CommonMessageHandler implements ResponseBodyAdvice<Object> {
    private static final String OAUTH_URI_START = "/oauth";
    private static final String SWAGGER_URI_START_1 = "/swagger-ui.html";
    private static final String SWAGGER_URI_START_2 = "/webjars";
    private static final String SWAGGER_URI_START_3 = "/v2";
    private static final String SWAGGER_URI_START_4 = "/swagger-resources";


    /**
     * 判断支持的类型
     */
    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 注意，这里必须返回true，否则不会执行beforeBodyWrite方法
        return true;
    }

    /**
     * 过滤
     */
    @Override
    public Object beforeBodyWrite(Object body,@NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (body instanceof RestResultMessage) {
            return body;
        }
        // oauth response不进行过滤
        String requestPath = request.getURI().getPath();
        if (requestPath.startsWith(OAUTH_URI_START) || requestPath.startsWith(SWAGGER_URI_START_1)
                || requestPath.startsWith(SWAGGER_URI_START_2) || requestPath
                .startsWith(SWAGGER_URI_START_3) || requestPath.startsWith(SWAGGER_URI_START_4)) {
            return body;
        }

        if (body instanceof String) {
            // String 不能封装成RestResultMessage， 否则会报错，应直接在controller层封装
            // StringHttpMessageConverter 报类型转换错误
            return body;
        }

        return new RestResultMessage<>(body);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestResultMessage<Object> errorHandler(Exception ex) {
        log.warn("handled exception: ", ex);
        return new RestResultMessage<>(() -> ex);
    }
}
