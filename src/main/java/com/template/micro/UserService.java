package com.template.micro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
@EnableAsync
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Async
    public CompletableFuture<ShopUser> createUser(ShopUser shopUser){

        shopUser.setPassword(encoder().encode(shopUser.getPassword()));
        return CompletableFuture.completedFuture(userRepository.save(shopUser));
    }

    public void createUserList(ArrayList<ShopUser> shopUserList){

        userRepository.saveAll(
                shopUserList
                .stream()
                .map(x -> {
                    x.setPassword(encoder().encode(x.getPassword()));
                    return x;
                }).collect(Collectors.toList())
        );

    }

    public List<ShopUser> shopUsers(){
        return userRepository.findAll();
    }

    @Async
    public CompletableFuture<ShopUser> shopUser(String userId){
        return CompletableFuture.completedFuture(userRepository.findByUserId(userId));
    }

    @Async
    public CompletableFuture<ShopUser> updateUser(String userId, ShopUser shopUser){

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

        return CompletableFuture.completedFuture(userRepository.save(shopUserToUpdate));
    }

    @Async
    public List<ShopUser> deleteUser(String userId){

        userRepository.removeByUserId(userId);

        return userRepository.findAll();

    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
