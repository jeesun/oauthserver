package com.simon.repository;

import com.simon.domain.jdbc.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {
    OauthUser findByUsername(String username);
    OauthUser findByPhone(String phone);
}
