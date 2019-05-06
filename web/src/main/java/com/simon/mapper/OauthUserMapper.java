package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.dto.AuthorityDto;
import com.simon.dto.StatisticDto;
import com.simon.model.OauthUser;
import com.simon.provider.OauthUserProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface OauthUserMapper extends MyMapper<OauthUser> {
    int updatePwdByPhone(@Param("phone") String phone, @Param("password") String password);

    List<OauthUser> findByMap(@Param("map") Map<String, Object> map);

    List<StatisticDto> sexRatio();

    /**
     * 使用Map查询
     *
     * @param map 查询条件
     * @return 结果列表
     */
    @ResultMap("SuperMap")
    @SelectProvider(type = OauthUserProvider.class, method = "getList")
    List<OauthUser> getList(Map<String, Object> map);

    /**
     * 获取未配置角色的用户
     *
     * @return 未配置角色的用户列表
     */
    List<AuthorityDto> getUnauthorized();

    /**
     * 根据手机号或者邮箱登录
     *
     * @param phone 手机号
     * @param email 邮箱
     * @return 记录个数
     */
    int countByPhoneOrEmail(@Param("phone") String phone, @Param("email") String email);

    /**
     * 根据用户id更新密码
     * @param id 用户id
     * @param password 新密码
     * @return 影响行数
     */
    int updatePasswordByUserId(@Param("id") Long id, @Param("password") String password);
}