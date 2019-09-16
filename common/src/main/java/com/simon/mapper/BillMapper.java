package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.model.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillMapper extends CrudMapper<Bill> {
    int updateBillStatusByOutTradeNo(String billStatus, String outTradeNo);

    List<Bill> getDtoList(@Param("map") Map<String, Object> params);

    @Override
    List<Bill> getList(@Param("map") Map<String, Object> map);
}