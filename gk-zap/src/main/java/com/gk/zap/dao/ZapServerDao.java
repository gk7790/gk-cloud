package com.gk.zap.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.zap.entity.ZapServerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Zap服务器
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-01-05
*/
@Mapper
public interface ZapServerDao extends BaseDao<ZapServerEntity> {
	
}