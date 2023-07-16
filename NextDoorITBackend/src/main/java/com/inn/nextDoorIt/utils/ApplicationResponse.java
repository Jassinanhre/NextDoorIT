package com.inn.nextDoorIt.utils;


import com.inn.nextDoorIt.POJO.Category;
import lombok.Data;

@Data
public class ApplicationResponse {
    private Object data;
    private int status;
    private String error;

    public ApplicationResponse(Object data, int status) {
        this.data = data;
        this.status = status;
    }

    public ApplicationResponse(int status, String error) {
        this.error = error;
        this.status = status;
    }

    public ApplicationResponse(String error) {
        this.error = error;
    }


}
