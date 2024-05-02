package com.company_management.repository;

import com.company_management.model.request.AccountSearchRequest;
import com.company_management.model.request.UserSearchRequest;
import com.company_management.model.response.AccountDetailResponse;
import com.company_management.model.response.AccountSearchResponse;
import com.company_management.model.response.PageResponse;
import com.company_management.model.response.UserSearchResponse;
import org.springframework.data.domain.Pageable;


public interface _UserCustomRepository {
    PageResponse<UserSearchResponse> searchAllUser(UserSearchRequest request, Pageable pageable);

    AccountDetailResponse findAccountDetail(Long id);

    PageResponse<AccountSearchResponse> searchAccount(AccountSearchRequest request, Pageable pageable);
}
