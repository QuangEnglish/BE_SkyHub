package com.company_management.config;

import com.company_management.exception.UserNotFoundException;
import com.company_management.model.entity.UserCustom;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserCustom)) {
            return Optional.ofNullable(100L);
//            throw new UserNotFoundException("Vui lòng đăng nhập tài khoản!");
        }
        UserCustom userCustom = (UserCustom) principal;
        return Optional.ofNullable(userCustom.getId());
    }
}
