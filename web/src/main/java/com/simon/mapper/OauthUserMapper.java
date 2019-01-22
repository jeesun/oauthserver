package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.StatisticDto;
import com.simon.model.OauthUser;
import com.simon.provider.OauthUserProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface OauthUserMapper extends MyMapper<OauthUser> {
    int updatePwdByPhone(@Param("phone") String phone, @Param("password") String password);

    List<OauthUser> findByMap(@Param("map") Map<String, Object> map);

    List<StatisticDto> sexRatio();

    /**
     * 使用Map查询
     * @param map 查询条件
     * @return 结果列表
     */
    @SelectProvider(type = OauthUserProvider.class, method = "getList")
    List<OauthUser> getList(Map<String, Object> map);
}