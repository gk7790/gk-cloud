package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.RelayGroupEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Relay授权组表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface RelayGroupDao extends BaseDao<RelayGroupEntity> {
    /**
     * 更具客户端ID获取分组
     * @param clientId 客户端ID
     * @return 返回组信息
     */
    RelayGroupEntity getByClientId(Long clientId);
}