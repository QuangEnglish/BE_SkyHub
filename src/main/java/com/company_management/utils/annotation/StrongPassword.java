package com.company_management.utils.annotation;

import com.company_management.utils.validator.StrongPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = StrongPasswordValidator.class) //chỉ định lớp StrongPasswordValidator là lớp cung cấp logic triển khai
@Target({ ElementType.METHOD, ElementType.FIELD })  //chỉ định annotation nay áp dụng cho mọi trường, mọi phương thức
@Retention(RetentionPolicy.RUNTIME)  //giữ lại trong thời gian chạy ứng dụng
@Documented //khai báo 1 annotation @StrongPassword được ghi vào trong API
public @interface StrongPassword {
    String message() default "Phải dài 8 ký tự và kết hợp chữ hoa, chữ thường, số, ký tự đặc biệt.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}