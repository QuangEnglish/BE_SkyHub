package com.company_management.utils.validator;


import com.company_management.utils.annotation.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$");
    }
//    ^: bắt đầu 1 chuỗi
//        (?=.*\d): ít nhất 1 chữ số
//        (?=.*[a-z]): ít nhất 1 chữ thường
//        (?=.*[A-Z]): ít nhất 1 chữ hoa
//        (?=.*[@#$%^&+=!*()]): ít nhất 1 ký tự ặc biệt
//        .{8,}: có ít nhất 8 ký tự
//    $: kết thúc chuỗi
}
