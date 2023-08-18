package com.learn.springaop.fileupload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResponse {

    private String fileStatus;
    private String downloadUri;

    public FileResponse(String fileIsUploaded, String s) {
        this.fileStatus=fileIsUploaded;
        this.downloadUri=s;
    }
}
