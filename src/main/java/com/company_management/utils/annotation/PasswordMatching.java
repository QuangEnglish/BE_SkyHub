package com.company_management.utils.annotation;

import com.company_management.utils.validator.PasswordMatchingValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordMatchingValidator.class)
@Target({ ElementType.TYPE }) //chỉ định annotation nay ch áp dụng cho lớp class
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatching {
    String password();

    String confirmPassword();

    String message() default "Mật khẩu phải trùng khớp!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {  //tạo ra 1 list mảng @PasswordMatching
        PasswordMatching[] value();   //cho phép áp dụng nhiều quy tắc kiểm tra tính khớp mật khẩu trên một đối tượng.
    }
}