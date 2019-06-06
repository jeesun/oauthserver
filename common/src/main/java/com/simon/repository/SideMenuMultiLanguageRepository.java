package com.simon.repository;

import com.simon.model.SideMenuMultiLanguage;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* @author jeesun
* @date 2019-05-30
**/
@Repository
public interface SideMenuMultiLanguageRepository extends JpaRepository<SideMenuMultiLanguage, Long> {
}
