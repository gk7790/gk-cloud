package com.gk.system.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.system.entity.SysWhitelistEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 白名单
 *
 * @author stark system@gmail.com
 * @since 1.0.0 2019-05-08
 */
@Mapper
public interface SysWhitelistDao extends BaseDao<SysWhitelistEntity> {

	List<SysWhitelistEntity> getList(Map<String, Object> params);
}