
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.common.config.AppConfig;
import com.simon.common.domain.BillStatus;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Autowired
    private BillRepository billRepository;

    @Override
    public long count() {
        return billRepository.count();
    }

    @Override
    public Bill save(Bill bill){
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> save(List<Bill> billList) {
        return billRepository.save(billList);
    }

    @Override
    public PageInfo<Bill> findAll(Integer pageNo, Integer pageSize, String orderBy){
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Bill> list = billMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<Bill> findAll(Pageable pageable){
        return billRepository.findAll(pageable);
    }

    @Override
    public List<Bill> findAll(){
        return billRepository.findAll();
    }

    @Override
    public void delete(Long id){
        billRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids){
        return billMapper.deleteByIds(ids);
    }

    @Override
    public Bill findById(Long id){
        return billRepository.findOne(id);
    }

    @Override
    public int insertList(List<Bill> list){
        return billMapper.insertList(list);
    }

    @Override
    public int insert(Bill bill){
        return billMapper.insert(bill);
    }

    @Override
    public int insertSelective(Bill bill){
        return billMapper.insertSelective(bill);
    }

    @Override
    public int updateByPrimaryKey(Bill bill){
        return billMapper.updateByPrimaryKey(bill);
    }

    @Override
    public int updateByPrimaryKeySelective(Bill bill){
        return billMapper.updateByPrimaryKeySelective(bill);
    }

    @Override
    public PageInfo<Bill> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize){
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)){
            PageHelper.startPage(pageNo, pageSize);
        }else{
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<Bill> list = billMapper.getList(params);
        return new PageInfo<>(list);
    }

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