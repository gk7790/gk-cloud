package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.common.tools.DataMap;
import com.gk.relay.dto.NodeDTO;
import com.gk.relay.entity.NodeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Relay节点主表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface NodeDao extends BaseDao<NodeEntity> {

    /**
     * 根据客户端获取节点
     * @param params 登入用户的信息
     * @return
     */
    List<NodeDTO> getNodeByClient(DataMap params);
}