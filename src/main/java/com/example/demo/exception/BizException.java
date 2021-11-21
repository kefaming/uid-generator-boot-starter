package com.example.demo.exception;

import lombok.Getter;

import java.io.PrintStream;
import java.io.PrintWriter;

@Getter
public class BizException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final int code;

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
