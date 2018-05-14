package com.template.micro.security;

import com.template.micro.ShopUser;
import com.template.micro.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
@Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        ShopUser shopUser = userRepository.findByUserId(userId);
        User.UserBuilder builder = null;

        if(shopUser == null){
            throw new UsernameNotFoundException(userId);
        }

        builder = User.withUsername(userId);
        builder.password(shopUser.getPassword());
        builder.roles("USER");

        log.info("userID {}", userId);

        return builder.build();
    }
}
