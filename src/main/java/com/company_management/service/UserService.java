package com.company_management.service;

import com.company_management.model.request.AccountSearchRequest;
import com.company_management.model.request.UserCustomEmployeeRequest;
import com.company_management.model.request.UserDetailRequest;
import com.company_management.model.request.UserSearchRequest;
import com.company_management.model.response.*;
import org.springframework.data.domain.Pageable;

public interface UserService {
    PageResponse<UserSearchResponse> searchUser(UserSearchRequest request, Pageable pageable);

    UserSearchResponse findUserDetailById(Long id);

    BasicResponse createUserDetail(UserDetailRequest request);

    AccountDetailResponse findAccountDetail(Long id);

    PageResponse<AccountSearchResponse> searchAccount(AccountSearchRequest request, Pageable pageable);

    void editUserCustom(UserCustomEmployeeRequest userCustomEmployeeRequest);
}
