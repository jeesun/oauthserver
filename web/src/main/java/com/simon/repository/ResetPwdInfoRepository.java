package com.simon.repository;

import com.simon.model.ResetPwdInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPwdInfoRepository extends JpaRepository<ResetPwdInfo, Long> {
}
