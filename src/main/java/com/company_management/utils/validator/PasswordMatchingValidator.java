package com.company_management.utils.validator;

import com.company_management.utils.annotation.PasswordMatching;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {

    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordMatching matching) { //để lấy giá trị của các thuộc tính từ annotation @PasswordMatching
        this.password = matching.password();
        this.confirmPassword = matching.confirmPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

        return (passwordValue != null) ? passwordValue.equals(confirmPasswordValue) : confirmPasswordValue == null;
    }
}
