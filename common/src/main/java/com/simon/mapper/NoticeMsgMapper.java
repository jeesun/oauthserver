package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.model.NoticeMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeMsgMapper extends CrudMapper<NoticeMsg> {
    List<NoticeMsg> getList(@Param("map") Map<String, Object> map);
}