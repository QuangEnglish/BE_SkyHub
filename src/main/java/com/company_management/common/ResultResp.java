package com.company_management.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class ResultResp<T> extends ResponseEntity<T> {

    private ResultResp(HttpStatus status, T body) {
        super(body, status);
    }

    public static ResultResp<Object> success(Object data) {
        BodyData<Object> bodyData = new BodyData<>(MsgCode.OK, data, HttpStatus.OK.getReasonPhrase());
        return new ResultResp<>(HttpStatus.OK, bodyData);
    }

    public static ResultResp<Object> created() {
        BodyData<Object> bodyData = new BodyData<>(MsgCode.OK, null, HttpStatus.OK.getReasonPhrase());
        return new ResultResp<>(HttpStatus.CREATED, bodyData);
    }


    public static ResultResp<Object> success(ObjectError success, Object data) {
        BodyData<Object> bodyData = new BodyData<>(success.getCode(), data, success.getMsgError());
        return new ResultResp<>(HttpStatus.OK, bodyData);
    }

    public static ResultResp<Object> badRequest(ObjectError objError) {
        BodyData<Object> bodyData = new BodyData<>(objError.getCode(), null, objError.getMsgError() == null ? HttpStatus.BAD_REQUEST.getReasonPhrase() : objError.getMsgError());
        return new ResultResp<>(HttpStatus.OK, bodyData);
    }

    @Setter
    @Getter
    public static class BodyData<T> implements Serializable {
        @JsonProperty("code")
        private String code;

        @JsonProperty("msgCode")
        private String message;

        @JsonProperty("data")
        private T data;

        BodyData(String code, T data, String message) {
            this.data = data;
            this.code = code;
            this.message = message;
        }

        public BodyData(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}