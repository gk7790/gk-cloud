package com.gk.system.service;


import com.gk.common.core.service.BaseService;
import com.gk.common.handler.LoginUser;
import com.gk.system.dto.SysMenuDTO;
import com.gk.system.entity.SysMenuEntity;

import java.util.List;
import java.util.Set;


/**
 * 菜单管理
 * 
 * @author Lowen
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

	SysMenuDTO get(Long id);

	void addMenu(SysMenuEntity dto);

	void update(SysMenuDTO dto);

	void delete(Long id);

	/**
	 * 菜单列表
	 *
	 * @param type 菜单类型
	 */
	List<SysMenuDTO> getAllMenuList(Integer type);

	/**
	 * 用户菜单列表
	 *
	 * @param user  用户
	 * @param type 菜单类型
	 */
	List<SysMenuDTO> getUserMenuList(LoginUser user, Integer type);

	/**
	 * 根据父菜单，查询子菜单
	 * @param pid  父菜单ID
	 */
	List<SysMenuDTO> getListPid(Long pid);

    Set<String> getUserPermissions(LoginUser user);
}