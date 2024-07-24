package com.tuaninfinity.hellosecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tuan Infinity on 7/24/2024 15:04:35
 *
 * @author Tuan Infinity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin implements UserDetails {
    private String username;

    private String password;

    private String[] roles;

    @Override
    // Phân quyền security
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String r : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ r));
        }
        return authorities;
    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
