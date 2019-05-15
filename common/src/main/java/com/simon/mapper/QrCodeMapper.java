package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.QrCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QrCodeMapper extends MyMapper<QrCode> {
    List<QrCode> getList(@Param("map") Map<String, Object> map);
}