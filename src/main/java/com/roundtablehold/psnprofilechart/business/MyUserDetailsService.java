package com.roundtablehold.psnprofilechart.business;

import com.roundtablehold.psnprofilechart.data.User;
import com.roundtablehold.psnprofilechart.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;

@Service
public class MyUserDetailsService implements UserDetailsService {


    PasswordEncoder passwordEncoder = passwordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)throw new UsernameNotFoundException(username);
        return new User(user.getId(), user.getUsername(), passwordEncoder.encode(user.getPassword()), user.isEnabled());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
