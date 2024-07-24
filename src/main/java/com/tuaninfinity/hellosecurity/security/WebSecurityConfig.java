package com.tuaninfinity.hellosecurity.security;

/**
 * Created by Tuan Infinity on 7/24/2024 15:19:54
 *
 * @author Tuan Infinity
 */

import com.tuaninfinity.hellosecurity.model.UserLogin;
import com.tuaninfinity.hellosecurity.repo.FakeDataUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Bean là các hoạt động
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class WebSecurityConfig {
    //    @Bean
//    @ConditionalOnMissingBean(UserDetailsService.class)
//    // Quản lý danh sách các user
//    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        String generatedPassword1 = passwordEncoder().encode("abc");
//        String generatedPassword2 = passwordEncoder().encode("xyz");
//        return new InMemoryUserDetailsManager(
//                User
//                        .withUsername("tuanba")
//                        .password(generatedPassword1)
//                        .roles("USER")
//                        .build(),
//                User
//                        .withUsername("linhntp")
//                        .password(generatedPassword2)
//                        .roles("USER")
//                        .build()
//                );
//
//    }

    private final FakeDataUserRepo fakeDataUserRepo;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            UserLogin u = fakeDataUserRepo.UserfindByUserName(username);
            if (u == null) {
                throw new UsernameNotFoundException("Sai rồi");
            }
            return u;
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())//hacker
                .authorizeHttpRequests(authorize -> authorize
                        //đường dẫn này người đăng nhập phải có role là LINH mới được truy cập
                        .requestMatchers("/api/get-user-linh")
                        .hasRole("LINH")
                        .requestMatchers("/api/get-user-tuan")
                        .hasRole("TUAN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }

}
