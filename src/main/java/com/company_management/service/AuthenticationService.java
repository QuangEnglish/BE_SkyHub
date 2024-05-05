package com.company_management.service;

import com.company_management.common.Constants;
import com.company_management.exception.AppException;
import com.company_management.exception.BadRequestException;
import com.company_management.model.dto.RoleDTO;
import com.company_management.model.entity.Role;
import com.company_management.model.entity.UserCustom;
import com.company_management.model.mapper.RoleMapper;
import com.company_management.model.request.AuthenticationRequest;
import com.company_management.model.request.ChangePasswordRequest;
import com.company_management.model.request.MailRequest;
import com.company_management.model.request.RegisterRequest;
import com.company_management.model.response.AuthenticationResponse;
import com.company_management.model.response.BasicResponse;
import com.company_management.repository.RoleRepository;
import com.company_management.repository.UserCustomRepository;
import com.company_management.repository.UserDetailRepository;
import com.company_management.utils.LogisticsMailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserCustomRepository userCustomRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleMapper roleMapper;
    private final UserDetailRepository userDetailRepository;
    private final EmailService emailService;
    private final ITemplateEngine templateEngine;

    @Transactional
    public void register(RegisterRequest request){
        UserCustom user = new UserCustom();
        Role role = roleRepository.findByRoleName(Constants.ROLE.ADMIN).orElseThrow(() -> new AppException("ERR01", "Không tìm thấy Quyền cho Tài khoản"));
        if(userCustomRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AppException("ERR01", "Email đã tồn tại!");
        }
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(Constants.STATUS_ACTIVE_INT);
        user.setRoles(roleSet);
        user.setActive(Constants.STATUS_ACTIVE_INT);
        user.setActiveCode(generateCode());
        user.setCreatedDate(new Date());
        userCustomRepository.save(user);
        Map<String, Object> params = LogisticsMailUtils.sendMailToCode(user.getActiveCode());
        Context context = new Context();
        context.setVariables(params);
        MailRequest mailRequest = MailRequest.builder()
                .toMail(user.getEmail())
                .html(true)
                .title("Công ty cổ phần truyền thông và dịch vụ Nodo")
                .content(templateEngine.process(MailRequest.CODE_REGISTER_PROVIDER_TEMPLATE, context))
                .build();
        emailService.send(mailRequest);
//        String jwtToken = jwtService.generateToken(user);
//        AuthenticationResponse response = new AuthenticationResponse();
//        response.setUsername(user.getUser());
//        response.setToken(jwtToken);
//        Set<RoleDTO> roleDTOS = user.getRoles().stream().map(roleMapper::toDto).collect(Collectors.toSet());
//        response.setRoles(roleDTOS);
//        return response;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        UserCustom user =
                userCustomRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException(
                        "Không tìm thấy User có địa chỉ email" + request.getEmail()));
        if (!user.getStatus().equals(Constants.STATUS_ACTIVE_INT)){
            throw new RuntimeException("User chưa được kích hoạt");
        }
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        }catch (Exception ex){
            throw new AppException("ERR02", "Email hoặc mật khẩu không đúng!");
        }
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUsername(user.getUser());
        response.setToken(jwtToken);
        Set<RoleDTO> roleDTOS = user.getRoles().stream().map(roleMapper::toDto).collect(Collectors.toSet());
        response.setRoles(roleDTOS);
        return response;
    }


    public boolean activeAccount(String activeCode) {
        UserCustom user = userCustomRepository.findByActiveCode(activeCode).orElseThrow(() -> new RuntimeException(
                "Wrong active code!!!"));
        user.setActiveCode(null);
        user.setStatus(Constants.STATUS_ACTIVE_INT);
        user.setActive(Constants.STATUS_ACTIVE_INT);
        userCustomRepository.save(user);
        return true;
    }

    public String generateCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public Boolean forgotPassword(String email) {
        UserCustom user =
                userCustomRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                        "User not found"));
        user.setForgotPassword(generateCode());
        userCustomRepository.save(user);
        return true;
    }

    public AuthenticationResponse validForgotCode(String forgotCode) {
       UserCustom user =
               userCustomRepository.findByForgotPassword(forgotCode).orElseThrow(() -> new RuntimeException("User " +
               "not found!!!"));
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUsername(user.getUser());
        response.setToken(jwtToken);
        return response;
    }

    public Boolean changePassword(ChangePasswordRequest request, Principal principal) {
        UserCustom user =
                userCustomRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Email " +
                        "not found!!!"));
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new RuntimeException("Confirm Password not same!!!");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userCustomRepository.save(user);
        return true;
    }

    public BasicResponse resendVerifyCode(Long id) {
        UserCustom user =
                userCustomRepository.findById(id).orElseThrow(() -> new BadRequestException("Có lỗi " +
                        "xảy ra: Không tìm thấy User theo id: " + id));
        String verifyCode = generateCode();
        user.setActiveCode(verifyCode);
        userCustomRepository.save(user);
        //Todo: Gui email code
        return new BasicResponse(200, "Mã xác thực mới đã được gửi vào email: " + user.getEmail());
    }
}
