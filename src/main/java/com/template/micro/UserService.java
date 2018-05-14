package com.template.micro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@EnableAsync
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void createUser(ShopUser shopUser){

        shopUser.setPassword(encoder().encode(shopUser.getPassword()));
        userRepository.save(shopUser);
    }

    public void createUserList(ArrayList<ShopUser> shopUserList){
        userRepository.saveAll(shopUserList);
    }

    @Async
    public CompletableFuture<List<ShopUser>> shopUsers(){
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    @Async
    public CompletableFuture<ShopUser> shopUser(String userId){
        return CompletableFuture.completedFuture(userRepository.findByUserId(userId));
    }

    @Async
    public void updateUser(String userId, ShopUser shopUser){

        ShopUser shopUserToUpdate = userRepository.findByUserId(userId);

        shopUserToUpdate.setEmail(shopUser.getEmail());
        shopUserToUpdate.setBirth(shopUser.getBirth());
        shopUserToUpdate.setPassword(shopUser.getPassword());
        shopUserToUpdate.setGender(shopUser.getGender());
        shopUserToUpdate.setType(shopUser.getType());
        shopUserToUpdate.setType(shopUser.getType());
        shopUserToUpdate.setMarketingEmail(shopUser.getMarketingEmail());
        shopUserToUpdate.setMarketingPush(shopUser.getMarketingPush());
        shopUserToUpdate.setMarketingSms(shopUser.getMarketingSms());

        userRepository.save(shopUserToUpdate);
    }

    @Async
    public void deleteUser(String userId){

        //userRepository.deleteByUserId(userId);
        userRepository.removeByUserId(userId);
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
