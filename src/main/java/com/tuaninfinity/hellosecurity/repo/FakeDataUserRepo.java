package com.tuaninfinity.hellosecurity.repo;

import com.tuaninfinity.hellosecurity.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tuan Infinity on 7/24/2024 15:54:48
 *
 * @author Tuan Infinity
 */
@Repository
public class FakeDataUserRepo {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    List<UserLogin> list = List.of(
            UserLogin.builder().username("tuanba").password(passwordEncoder.encode("130903")).roles(new String[]{"TUAN"}).build(),
            UserLogin.builder().username("linhntp").password(passwordEncoder.encode("130903")).roles(new String[]{"LINH"}).build()
    );

    //load user
    public UserLogin UserfindByUserName(String username){
        for (UserLogin u : list){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
}
