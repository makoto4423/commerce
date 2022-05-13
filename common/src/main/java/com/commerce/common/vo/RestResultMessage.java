package com.commerce.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Supplier;

@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RestResultMessage<T> {

    private T content;
    private boolean success;
    private String errorCode;
    private String errorMessage;

    public RestResultMessage(Supplier<? extends Throwable> supplier) {
        this.setContent(null);
        this.setSuccess(false);
        this.setErrorMessage(supplier.get().getLocalizedMessage());
    }

    public RestResultMessage(T t) {
        this.setContent(t);
        this.setSuccess(true);
    }
}
