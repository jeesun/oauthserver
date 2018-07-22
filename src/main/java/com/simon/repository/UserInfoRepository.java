package com.simon.repository;

import com.simon.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by simon on 2016/8/13.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findById(Long id);
    UserInfo findByUserId(Long userId);
    UserInfo findByPhone(String phone);
    UserInfo findByUsername(String username);
    UserInfo findByEmail(String email);
}
