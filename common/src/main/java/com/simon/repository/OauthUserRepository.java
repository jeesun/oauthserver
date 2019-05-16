package com.simon.repository;

import com.simon.model.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {
    OauthUser findByUsername(String username);
    OauthUser findByPhone(String phone);
    OauthUser findById(Long id);
    OauthUser findByEmail(String email);
}
