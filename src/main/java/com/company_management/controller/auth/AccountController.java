package com.company_management.controller.auth;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.MenuItemDTO;
import com.company_management.model.dto.RoleDTO;
import com.company_management.model.request.AccountSearchRequest;
import com.company_management.model.request.UserCustomEmployeeRequest;
import com.company_management.model.response.AccountSearchResponse;
import com.company_management.model.response.PageResponse;
import com.company_management.service.MenuItemService;
import com.company_management.service.RoleMenuItemService;
import com.company_management.service.RoleService;
import com.company_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final RoleService roleService;
    private final MenuItemService menuItemService;
    private final RoleMenuItemService roleMenuItemService;

    @PostMapping("/getAll")
    public ResponseEntity<PageResponse<AccountSearchResponse>> findAll(@RequestBody AccountSearchRequest request,
                                                                       @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(userService.searchAccount(request, pageable), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getAllRole(){
        return new ResponseEntity<>(roleService.getAllRole(), HttpStatus.OK);
    }

    @GetMapping("role/{roleName}")
    public ResponseEntity<RoleDTO> getRoleByRoleName(@PathVariable String roleName){
        return new ResponseEntity<>(roleService.getRoleByRoleName(roleName), HttpStatus.OK);
    }

    @GetMapping("menu-item")
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItem(){
        return new ResponseEntity<>(menuItemService.getAll(), HttpStatus.OK);
    }

    @PutMapping  //cấp phát tài khoản
    public ResultResp<Object> updateEmployeeAccount(@Valid @RequestBody UserCustomEmployeeRequest userCustomEmployeeRequest){
        userService.editUserCustom(userCustomEmployeeRequest);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }


    //cập nhật menuItem cho role
    @PostMapping("/update-role-menu-item/{roleId}")
    public ResultResp<Object> updateRoleMenuItem(@PathVariable("roleId") Long roleId,
                                                 @RequestBody List<Long> menuItemIds) {
        roleMenuItemService.createOrUpdate(roleId, menuItemIds);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }




}
