package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class LoginAuthenticationService {
    public boolean Authenticate(String userName, String password) {
        return userName.equalsIgnoreCase("Shashwat") && password.equals("Hello");
    }
}
