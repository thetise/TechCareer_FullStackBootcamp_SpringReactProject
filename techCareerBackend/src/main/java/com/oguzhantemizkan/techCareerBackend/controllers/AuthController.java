package com.oguzhantemizkan.techCareerBackend.controllers;

import com.oguzhantemizkan.techCareerBackend.entities.User;
import com.oguzhantemizkan.techCareerBackend.requests.UserRequest;
import com.oguzhantemizkan.techCareerBackend.responses.AuthResponse;
import com.oguzhantemizkan.techCareerBackend.security.JwtTokenProvider;
import com.oguzhantemizkan.techCareerBackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;



    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Bu kod, bir Spring Boot uygulamasında bir kullanıcının giriş yapmasını işleyen bir controller methodunu temsil eder.
    // Bu kodun amacı, bir istemciden gelen kullanıcı adı ve şifre bilgilerini alarak, bu bilgileri kullanarak
    // bir JWT (Json Web Token) üretmek ve kullanıcıya bu token'ı içeren bir yanıt göndermektir.
    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user = userService.getOneUserByName(loginRequest.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Bearer " + jwtToken);
        authResponse.setUserId(user.getId());
        return authResponse;
    }

    // Bu kod, yeni bir kullanıcı kaydı oluşturmayı işleyen basit bir endpoint sağlar. Eğer kullanıcı adı
    // zaten kullanımda ise, hata durumunu bildirir; aksi takdirde, yeni kullanıcıyı oluşturur ve
    // başarılı bir kayıt mesajı ile birlikte HTTP 201 Created durum koduyla yanıt verir.
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        if(userService.getOneUserByName(registerRequest.getUserName()) != null) {
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.createUser(user);
        authResponse.setMessage("User successfully registered.");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
}
