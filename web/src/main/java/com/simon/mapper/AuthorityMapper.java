package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.AuthorityDto;
import com.simon.model.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuthorityMapper extends MyMapper<Authority> {
    /**
     * 查询权限Dto
     * @param params 查询参数
     * @return 权限Dto
     */
    List<AuthorityDto> getDtoList(@Param("map") Map<String, Object> params);

    /**
     * 根据userIds批量删除记录
     * @param userIds 用户ids
     * @return 影响记录行数
     */
    int deleteByUserIds(@Param("userIds") String userIds);

    List<Authority> getList(@Param("map") Map<String, Object> map);
}