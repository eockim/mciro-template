package com.template.micro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<ShopUser, Long > {

    @Transactional
    ShopUser findByUserId(String userId);

    @Transactional
    Long deleteByUserId(String userId);

    @Transactional
    List<ShopUser> removeByUserId(String userId);

}
