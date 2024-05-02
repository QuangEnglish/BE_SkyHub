package com.company_management.service;

import com.company_management.exception.AppException;
import com.company_management.exception.BadRequestException;
import com.company_management.model.dto.UserCustomDTO;
import com.company_management.model.entity.UserCustom;
import com.company_management.model.entity.UserDetail;
import com.company_management.repository.UserCustomRepository;
import com.company_management.repository.UserDetailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {
    private final UserCustomRepository userCustomRepository;
    private final UserDetailRepository userDetailRepository;
    private static final String SERCRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public UserCustomDTO extractUser(String token) {
        try {
            Claims claims = extractAllClaims(token);
            String jsonUserCustomDTO = claims.get("user").toString();

            ObjectMapper mapper = new ObjectMapper();
            UserCustomDTO user = mapper.readValue(jsonUserCustomDTO, UserCustomDTO.class);

            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserCustom user){
        Map<String, Object> claims = new HashMap<>();
        String jsonUserCustom = "";

        try {
            UserCustom userCustom =
                    userCustomRepository.findByEmail(user.getUsername()).orElseThrow(() -> new BadRequestException("Không tìm thấy User"));
            UserDetail userDetail = userDetailRepository.findById(userCustom.getUserDetailId()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy nhân viên!")
            );
            UserCustomDTO userCustomDTO = new UserCustomDTO();
            userCustomDTO.setId(userCustom.getId());
            userCustomDTO.setUserDetailId(userCustom.getUserDetailId());
            userCustomDTO.setUserDetailName(userDetail.getEmployeeName());
            userCustomDTO.setUserName(userCustom.getUsername());
            userCustomDTO.setEmail(userCustom.getEmail());
            jsonUserCustom = convertObjectToJson(userCustomDTO);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        claims.put("user", jsonUserCustom);
        return generateToken(claims, user);
    }
    public String generateToken(Map<String, Object> extraClaims, UserCustom user){
        return Jwts.builder()
                .addClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SERCRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}