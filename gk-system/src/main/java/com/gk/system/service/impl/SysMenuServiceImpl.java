package com.gk.system.service.impl;

import com.gk.common.constant.Constant;
import com.gk.common.core.service.impl.BaseServiceImpl;
import com.gk.common.exception.ErrorCode;
import com.gk.common.exception.GkException;
import com.gk.common.handler.LoginUser;
import com.gk.common.utils.ConvertUtils;
import com.gk.common.utils.HttpContextUtils;
import com.gk.common.utils.TreeUtils;
import com.gk.common.utils.ValueUtils;
import com.gk.system.dao.SysMenuDao;
import com.gk.system.dto.SysMenuDTO;
import com.gk.system.dto.SysMenuMeta;
import com.gk.system.entity.SysLanguageEntity;
import com.gk.system.entity.SysMenuEntity;
import com.gk.system.service.SysLanguageService;
import com.gk.system.service.SysMenuService;
import com.gk.system.service.SysRoleMenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysLanguageService sysLanguageService;

    protected SysMenuServiceImpl(SysMenuDao baseDao) {
        super(baseDao);
    }

    @Override
	public SysMenuDTO get(Long id) {
		SysMenuEntity entity = baseDao.getById(id, HttpContextUtils.getLanguage());

		SysMenuDTO dto = ConvertUtils.sourceToTarget(entity, SysMenuDTO.class);

		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addMenu(SysMenuEntity entity) {
		//保存菜单
		insert(entity);
		saveLanguage(entity.getId(), "meta.tile", ValueUtils.defaultValue(entity.getMeta().getTitle(), entity.getName()));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysMenuDTO dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

		//上级菜单不能为自身
		if(entity.getId().equals(entity.getPid())){
			throw new GkException(ErrorCode.SUPERIOR_MENU_ERROR);
		}

		//更新菜单
		updateById(entity);
		saveLanguage(entity.getId(), "meta.tile", ValueUtils.defaultValue(entity.getMeta().getTitle(), entity.getName()));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		//删除菜单
		deleteById(id);

		//删除菜单国际化
		sysLanguageService.deleteLanguage("sys_menu", id);

		//删除角色菜单关系
		sysRoleMenuService.deleteByMenuId(id);
	}

	@Override
	public List<SysMenuDTO> getAllMenuList(Integer menuType) {
		List<SysMenuEntity> menuList = baseDao.getMenuList(menuType);
        List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);
        if (CollectionUtils.isNotEmpty(dtoList)) {
            Map<Long, String> language = sysLanguageService.getLanguage("sys_menu", "meta.tile", HttpContextUtils.getLanguage());
            fillMenuTitle(dtoList, language);
        }
        return TreeUtils.build(dtoList, Constant.MENU_ROOT);
	}

	@Override
	public List<SysMenuDTO> getUserMenuList(LoginUser user, Integer menuType) {
		List<SysMenuEntity> menuList;

		//系统管理员，拥有最高权限
		if(user.getIsAdmin()){
			menuList = baseDao.getMenuList(menuType);
		}else {
			menuList = baseDao.getUserMenuList(user.getUserId(), menuType, HttpContextUtils.getLanguage());
		}

		List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

		return TreeUtils.build(dtoList);
	}

	@Override
	public List<SysMenuDTO> getListPid(Long pid) {
		List<SysMenuEntity> menuList = baseDao.getListPid(pid);

		return ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);
	}

	private void saveLanguage(Long tableId, String fieldName, String fieldValue){
		sysLanguageService.saveOrUpdate("sys_menu", tableId, fieldName, fieldValue, HttpContextUtils.getLanguage());
	}

    @Override
    public Set<String> getUserPermissions(LoginUser user) {
        return Set.of();
    }

    public void fillMenuTitle(List<SysMenuDTO> menus, Map<Long, String> langMap) {
        for (SysMenuDTO menu : menus) {
            if (ObjectUtils.isNotEmpty(menu.getMeta()) && langMap.containsKey(menu.getId())) {
                String title = langMap.get(menu.getId());
                if (StringUtils.isNotBlank(title)) {
                    menu.getMeta().setTitle(title);
                }
            }
        }
    }
}