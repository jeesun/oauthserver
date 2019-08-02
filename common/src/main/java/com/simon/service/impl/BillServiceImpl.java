
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.domain.BillStatus;
import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.common.utils.BeanUtils;
import com.simon.common.utils.snowflake.SnowFlakeId;
import com.simon.mapper.BillMapper;
import com.simon.model.Bill;
import com.simon.model.OauthUser;
import com.simon.repository.BillRepository;
import com.simon.request.BillRequest;
import com.simon.service.BillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
* @author SimonSun
* @date 2018-11-20
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class BillServiceImpl extends CrudServiceImpl<Bill, Long> implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill createBill(BillRequest billRequest) {
        Bill bill = new Bill();
        BeanUtils.copyPropertiesIgnoreNull(billRequest, bill);
        //设置订单状态为待支付
        bill.setBillStatus(String.valueOf(BillStatus.WAIT_PAY.getCode()));
        //商户订单号，必须要有
        bill.setOutTradeNo(String.valueOf(SnowFlakeId.getId()));
        billMapper.insert(bill);
        return bill;
    }

    @Override
    public Bill createBill(OauthUser oauthUser, BillRequest billRequest) {
        LocalDateTime createDate = LocalDateTime.now();
        Bill bill = new Bill();
        bill.setUserId(oauthUser.getId());
        bill.setCreateBy(oauthUser.getId());
        bill.setCreateDate(createDate);
        bill.setBillDate(createDate);
        BeanUtils.copyPropertiesIgnoreNull(billRequest, bill);
        //设置订单状态为待支付
        bill.setBillStatus(String.valueOf(BillStatus.WAIT_PAY.getCode()));
        //商户订单号，必须要有
        bill.setOutTradeNo(String.valueOf(SnowFlakeId.getId()));
        billMapper.insert(bill);
        return bill;
    }

    @Override
    public void billPayed(String outTradeNo) {
        billMapper.updateBillStatusByOutTradeNo(String.valueOf(BillStatus.PAYED.getCode()), outTradeNo);
    }

    @Override
    public void billPayed(Bill bill) {
        billMapper.updateBillStatusByOutTradeNo(String.valueOf(BillStatus.PAYED.getCode()), bill.getOutTradeNo());
    }

    @Override
    public void createFreeBill(OauthUser oauthUser, BillRequest billRequest) {

    }

    @Override
    public void billPayFailed(String outTradeNo) {
        billMapper.updateBillStatusByOutTradeNo(String.valueOf(BillStatus.WAIT_PAY.getCode()), outTradeNo);
    }

    @Override
    public PageInfo<Bill> getDtoList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Bill> list = billMapper.getDtoList(params);
        return new PageInfo<>(list);
    }
}