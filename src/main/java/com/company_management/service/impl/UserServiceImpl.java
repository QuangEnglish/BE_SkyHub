package com.company_management.service.impl;

import com.company_management.common.DataUtil;
import com.company_management.common.DateUtil;
import com.company_management.exception.AppException;
import com.company_management.exception.BadRequestException;
import com.company_management.model.entity.*;
import com.company_management.model.mapper.ContractMapper;
import com.company_management.model.mapper.RoleMapper;
import com.company_management.model.mapper.SocialInsuranceMapper;
import com.company_management.model.mapper.WageMapper;
import com.company_management.model.request.AccountSearchRequest;
import com.company_management.model.request.UserCustomEmployeeRequest;
import com.company_management.model.request.UserDetailRequest;
import com.company_management.model.request.UserSearchRequest;
import com.company_management.model.response.*;
import com.company_management.repository.*;
import com.company_management.service.UserService;
import com.company_management.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCustomRepository userCustomRepository;
    private final UserDetailRepository userDetailRepository;
    private final ContractRepository contractRepository;
    private final SocialInsuranceRepository socialInsuranceRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final WageRepository wageRepository;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final ContractMapper contractMapper;
    private final SocialInsuranceMapper socialInsuranceMapper;
    private final WageMapper wageMapper;
    private final UserCustomRoleRepository userCustomRoleRepository;

    @Override
    public PageResponse<UserSearchResponse> searchUser(UserSearchRequest request, Pageable pageable) {
        return userCustomRepository.searchAllUser(request, pageable);
    }

    @Override
    public UserSearchResponse findUserDetailById(Long id) {
            UserCustom userCustom = userCustomRepository.findById(id).orElseThrow(() -> new BadRequestException(
                    "Không tìm thấy User theo id" + id));
            UserDetail userDetail =
                    userDetailRepository.findById(userCustom.getUserDetailId()).orElseThrow(() -> new AppException(
                            "API-500", "Có lỗi xảy ra"));
            UserSearchResponse result = new UserSearchResponse();
            result.setId(userCustom.getId());
            result.setFullName(userCustom.getUsername());
            result.setEmail(userCustom.getEmail());
            return result;
    }

    @Override
    public BasicResponse createUserDetail(UserDetailRequest request) {
        UserCustom user =
                userCustomRepository.findById(request.getUserId()).orElseThrow(() -> new BadRequestException("Có lỗi " +
                        "xảy ra: Không tìm thấy User theo id: " + request.getUserId()));
        UserDetail userDetail =
                userDetailRepository.findById(user.getUserDetailId()).orElseThrow(() -> new AppException("API-500",
                        "Có lỗi xảy ra"));
        Department department =
                departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new BadRequestException(
                        "Có lỗi xảy ra: Phòng ban không tồn tại!"));
        Position position =
                positionRepository.findById(request.getPositionId()).orElseThrow(() -> new BadRequestException(
                        "Có lỗi xảy ra: Chức danh không tồn tại!"));
        userDetail.setGender(request.getGender());
        userDetail.setBirthday(DateUtil.string2Date(request.getBirthday()));
        userDetail.setAddress(request.getAddress());

        Contract contract = contractMapper.toEntity(request.getContract());
        contractRepository.save(contract);
        SocialInsurance socialInsurance = socialInsuranceMapper.toEntity(request.getSocialInsurance());
        socialInsurance.setUserDetailId(userDetail.getId());
        socialInsuranceRepository.save(socialInsurance);
        Wage wage = wageMapper.toEntity(request.getWage());
        wageRepository.save(wage);

        userDetailRepository.save(userDetail);
        return new BasicResponse(200, "Thành công");
    }

    @Override
    public AccountDetailResponse findAccountDetail(Long id) {
        if(DataUtil.isNullOrZero(id)){
            throw new BadRequestException("Dữ liệu không hợp lệ");
        }
        UserCustom user = userCustomRepository.findById(id).orElseThrow(() -> new BadRequestException("Có lỗi " +
                "xảy ra: Không tìm thấy User theo id: " + id));
        return null;
    }

    @Override
    public PageResponse<AccountSearchResponse> searchAccount(AccountSearchRequest request, Pageable pageable) {
        return userCustomRepository.searchAccount(request, pageable);
    }

    @Override
    @Transactional
    public void editUserCustom(UserCustomEmployeeRequest userCustomEmployeeRequest) {
        UserCustom userCustom = userCustomRepository.findById(userCustomEmployeeRequest.getId()).orElseThrow(
                () -> new AppException("ERR01", "Tài khoản không tồn tại!")
        );
        if(!DataUtils.isNullOrEmpty(userCustomEmployeeRequest.getUserDetailId())){
            userCustom.setUserDetailId(userCustomEmployeeRequest.getUserDetailId());
        }
        userCustomRepository.save(userCustom);
    }

    @Override
    @Transactional
    public void updateUserRoles(String email, List<String> roleNames) {
        Optional<UserCustom> user = userCustomRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("Email này không tồn tại");
        }

        // Xóa tất cả các quyền cũ của người dùng
        userCustomRoleRepository.deleteByUserId(user.get().getId());

        // Thêm các quyền mới cho người dùng
        for (String roleName : roleNames) {
            Optional<Role> role = roleRepository.findByRoleName(roleName);
            role.ifPresent(value -> userCustomRoleRepository.insertUserRole(user.get().getId(), value.getId()));
        }
    }

}
