package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.NoticeMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeMsgMapper extends MyMapper<NoticeMsg> {
    List<NoticeMsg> getList(@Param("map") Map<String, Object> map);
}