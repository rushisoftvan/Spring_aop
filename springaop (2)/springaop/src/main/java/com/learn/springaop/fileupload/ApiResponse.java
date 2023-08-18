package com.learn.springaop.fileupload;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class ApiResponse <T>{
    private T data ;
    private Integer statusCode;
}
