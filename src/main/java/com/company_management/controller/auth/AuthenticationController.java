package com.company_management.controller.auth;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.request.AuthenticationRequest;
import com.company_management.model.request.ChangePasswordRequest;
import com.company_management.model.request.RegisterRequest;
import com.company_management.model.response.AuthenticationResponse;
import com.company_management.model.response.BasicResponse;
import com.company_management.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResultResp<Object> register(@RequestBody RegisterRequest request){
        authenticationService.register(request);
        return ResultResp.success(ErrorCode.CREATED_OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }

    @GetMapping("/{activeCode}")
    public ResponseEntity<BasicResponse> activeAccount(@PathVariable String activeCode){
        if(authenticationService.activeAccount(activeCode)){
            return new ResponseEntity<>(new BasicResponse(200, "Kích hoạt tài khoản thành công"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BasicResponse(400, "Mã kích hoạt không đúng"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/forgot-password/{email}")
    public ResponseEntity<Boolean> forgotPassword(@PathVariable String email){
        return new ResponseEntity<>(authenticationService.forgotPassword(email), HttpStatus.OK);
    }

    @PostMapping("/getForgotCode/{forgotCode}")
    public ResponseEntity<AuthenticationResponse> validForgotCode(@PathVariable String forgotCode){
        return new ResponseEntity<>(authenticationService.validForgotCode(forgotCode), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<BasicResponse> changePassword(@RequestBody ChangePasswordRequest request){
        if(authenticationService.changePassword(request)){
            return new ResponseEntity<>(new BasicResponse(200, "Thay đổi mật khẩu thành công"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BasicResponse(500, "Thay đổi mật khẩu không thành công"), HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/resend-code")
    private ResponseEntity<BasicResponse> resendCode(@RequestParam(name = "id") Long id){
        return new ResponseEntity<>(authenticationService.resendVerifyCode(id), HttpStatus.OK);
    }
}
