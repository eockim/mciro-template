package com.template.micro;

import com.template.micro.security.AuthenticationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@RestController
@EnableAsync
@Slf4j
public class MicroApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MicroApplication.class, args);
    }

    @GetMapping("users")
    public Mono<List<ShopUser>> users(){

        return Mono.fromCompletionStage(userService.shopUsers());

    }

    @PostMapping("users")
    public String createUser(ShopUser shopUser){

        //userService.createUser(new ShopUser("hyung1988", "hyungjoon6876@gmail.com", "kim", "test", "010-6363-6876", "M", "1988-10-20", "direct","Y","Y","Y") );
        userService.createUser(shopUser);

        return "post users";
    }

    @PostMapping("users/createWithArray")
    public String createWithArray(@RequestBody ArrayList<ShopUser> shopUserList){

        userService.createUserList(shopUserList);
        return "createWithArray";
    }

    @PostMapping("users/login")
    public String login(AuthenticationRequest authenticationRequest){

        return "login";
    }

    @GetMapping("users/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("users/{userId}")
    public Mono<ShopUser> user(@PathVariable String userId){
        return Mono.fromCompletionStage(userService.shopUser(userId));
    }

    @PutMapping("users/{userId}")
    public String updateUser(@PathVariable String userId, @RequestBody ShopUser shopUser){

        userService.updateUser(userId, shopUser);
        return "user : " + userId;
    }

    @DeleteMapping("users/{userId}")
    public String deleteUser(@PathVariable String userId){

        userService.deleteUser(userId);
        return "delete user : " + userId;
    }
}
