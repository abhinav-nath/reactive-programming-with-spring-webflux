package com.codecafe.reactive.flux_mono_playground;

import lombok.Data;

@Data
public class CustomException extends Throwable {

    private String message;

    public CustomException(Throwable e) {
        this.message = e.getMessage();
    }

}