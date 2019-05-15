package com.simon.repository;

import com.simon.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2018-11-20
**/
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findByOutTradeNo(String outTradeNo);
}
