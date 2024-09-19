package com.example.sbtest.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CommonContext {
    private String errorMessage;
    private int httpStatusCode;
}
