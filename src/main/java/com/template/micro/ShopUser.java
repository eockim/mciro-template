package com.template.micro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
public class ShopUser {

    public ShopUser(){}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
    private String userId;
    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String phone;
    @NonNull
    private String gender;
    @NonNull
    private String birth;
    @NonNull
    private String type;
    @NonNull
    private String marketingEmail;
    @NonNull
    private String marketingSms;
    @NonNull
    private String marketingPush;
}
