package com.personalfinance.management.service;

import com.personalfinance.management.entity.UserEntity;
import com.personalfinance.management.model.user.LoginUserRequest;
import com.personalfinance.management.model.TokenResponse;
import com.personalfinance.management.repository.UserRepository;
import com.personalfinance.management.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public TokenResponse login(LoginUserRequest request){
        validationService.validate(request);

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"name or password wrong"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"name or password wrong");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        Long expiredAt = jwtUtil.getExpirationTime();

        return TokenResponse.builder().token(token).expiredAt(expiredAt).build();
    }

}
