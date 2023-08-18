package com.learn.springaop.fileupload.exception;

public class FileIsNotAvailableException extends RuntimeException{
           public FileIsNotAvailableException(String msg){
              super(msg);
           }
}
