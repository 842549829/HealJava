package com.his.heal.dto;

import lombok.Data;

@Data
public class ResultDto<T> {
    private int code;
    private String message;
    private T data;
}
