package com.commerce.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.commerce.common.vo.RestResultMessage;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 *  feign 只用于系统内部通信，而内部返回结果都统一被 {@link com.commerce.common.config.CommonMessageHandler}代理，
 *  生成{@link RestResultMessage} 的返回结果，故直接进行json强转
 *  省去每次内部通信都对{@link RestResultMessage#isSuccess()} 进行判断，代价就是这里各种强转，且限定了只能用于内部通信
 */
@Component
public class FeignResponseInterceptor implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (response.status() == 404) return Util.emptyValueOf(type);
        if (response.body() == null) return null;
        String s = Util.toString(response.body().asReader());
        RestResultMessage<?> message = JSONObject.parseObject(s, RestResultMessage.class);
        if (!message.isSuccess()) throw new RuntimeException(message.getErrorMessage());
        if (message.getContent() instanceof JSONObject) {
            try {
                return ((JSONObject) message.getContent()).toJavaObject(Class.forName(type.getTypeName()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return message.getContent();
    }
}
