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
        if(authentication!=null){
            Object principal = authentication.getPrincipal();
            if (!(principal instanceof UserCustom)) {
                return Optional.ofNullable(1000L);
            }
            UserCustom userCustom = (UserCustom) principal;
            return Optional.ofNullable(userCustom.getId());
        }
        return Optional.ofNullable(1000L);
    }
}
