package com.company_management.exception;

import com.company_management.common.ResultResp;
import com.company_management.model.response.ValidationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AppException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleNoDataFoundException(AppException ex) {
        log.error(ex.getMessage(), ex);
        return new ResultResp.BodyData<>(ex.getCode() == null ? "ERR00" : ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleGlobalException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResultResp.BodyData<>("ERR01", "Đã có lỗi trong quá trình xử lý, vui lòng thực hiện vào lúc khác!");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        ValidationResponse<Object> validationResponse = new ValidationResponse<>();
        List<ObjectError> objectError = ex.getBindingResult().getAllErrors();
        List<String> errMsgList = new ArrayList<>();
        if (!objectError.isEmpty()) {
            for (ObjectError err : objectError){
                errMsgList.add(err.getDefaultMessage());
            }
        }
        validationResponse.setData(errMsgList);
        validationResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return validationResponse;
    }
    @ExceptionHandler(BadRequestException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleBadRequestException(BadRequestException ex) {
        log.error(ex.getMessage(), ex);
        return new ResultResp.BodyData<>("ERR02", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleBadRequestException(UserNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResultResp.BodyData<>("ERR03", "Tài khoản người dùng không hợp lệ!");
    }
}