package com.gk.system.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.system.entity.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 岗位管理
*
* @author Mark sunlightcs@gmail.com
*/
@Mapper
public interface SysPostDao extends BaseDao<SysPostEntity> {
	
}