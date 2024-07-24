package com.tuaninfinity.hellosecurity.controller;

import com.tuaninfinity.hellosecurity.model.UserLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Tuan Infinity on 7/24/2024 15:05:50
 *
 * @author Tuan Infinity
 */

@RestController
@RequestMapping("api")

public class LoginController {
    @GetMapping("get-user-tuan")
    public ResponseEntity<?> getUser() {
        UserLogin u = UserLogin.builder()
                .username("abc")
                .password("123")
                .roles(new String[]{"ADMIN"})
                .build();
        return ResponseEntity.ok().body(u);
    }
    @GetMapping("get-user-linh")
    public ResponseEntity<?> getUserLinh() {
        UserLogin u = UserLogin.builder()
                .username("linhntp")
                .password("123")
                .roles(new String[]{"CILENT"})
                .build();
        return ResponseEntity.ok().body(u);
    }

    @GetMapping(value = "user")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
