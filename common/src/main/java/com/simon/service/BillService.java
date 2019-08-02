package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.CrudService;
import com.simon.model.Bill;
import com.simon.model.OauthUser;
import com.simon.request.BillRequest;

import java.util.Map;

/**
* @author SimonSun
* @date 2018-11-20
**/
public interface BillService extends CrudService<Bill, Long> {
    Bill createBill(BillRequest billRequest);

    Bill createBill(OauthUser oauthUser, BillRequest billRequest);
    void billPayed(String outTradeNo);
    void billPayed(Bill bill);

    void createFreeBill(OauthUser oauthUser, BillRequest billRequest);

    void billPayFailed(String outTradeNo);

    PageInfo<Bill> getDtoList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy);
}