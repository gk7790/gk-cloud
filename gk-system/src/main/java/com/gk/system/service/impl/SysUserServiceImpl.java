package com.gk.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gk.common.core.service.impl.BaseServiceImpl;
import com.gk.common.enums.SysEnum;
import com.gk.common.page.PageData;
import com.gk.common.password.PasswordUtils;
import com.gk.common.utils.ConvertUtils;
import com.gk.security.entity.SysUser;
import com.gk.security.utils.SecurityUtils;
import com.gk.system.dao.SysUserDao;
import com.gk.system.dto.SysUserDTO;
import com.gk.system.entity.SysUserEntity;
import com.gk.system.service.SysDeptService;
import com.gk.system.service.SysRoleUserService;
import com.gk.system.service.SysUserPostService;
import com.gk.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author Lowen
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    private final SysRoleUserService sysRoleUserService;
	private final SysDeptService sysDeptService;
	private final SysUserPostService sysUserPostService;
    private final SecurityUtils securityUtils;

    protected SysUserServiceImpl(SysUserDao baseDao, SysRoleUserService sysRoleUserService, SysDeptService sysDeptService, SysUserPostService sysUserPostService, SecurityUtils securityUtils) {
        super(baseDao);
        this.sysRoleUserService = sysRoleUserService;
        this.sysDeptService = sysDeptService;
        this.sysUserPostService = sysUserPostService;
        this.securityUtils = securityUtils;
    }

    @Override
	public PageData<SysUserDTO> page(Map<String, Object> params) {
		//转换成like
		paramsToLike(params, "username");

		//分页
		IPage<SysUserEntity> page = getPage(params, "t1.created_at", false);

        //普通管理员，只能查询所属部门及子部门的数据
        SysUser user = securityUtils.getCurrentUser();
        if (user.getSuperAdmin() == SysEnum.sAdmin.NO.value()) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
			params.put("selfId", user.getId());
        }

		//查询
		List<SysUserEntity> list = baseDao.getList(params);

		return getPageData(list, page.getTotal(), SysUserDTO.class);
	}

	@Override
	public List<SysUserDTO> list(Map<String, Object> params) {
		//普通管理员，只能查询所属部门及子部门的数据
        SysUser user = securityUtils.getCurrentUser();
        if (user.getSuperAdmin() == SysEnum.sAdmin.NO.value()) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
            params.put("selfId", user.getId());
        }

		List<SysUserEntity> entityList = baseDao.getList(params);

		return ConvertUtils.sourceToTarget(entityList, SysUserDTO.class);
	}

	@Override
	public SysUserDTO get(Long id) {
		SysUserEntity entity = baseDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	public SysUserDTO getByUsername(String username) {
		SysUserEntity entity = baseDao.getByUsername(username);
		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

		//密码加密
		String password = PasswordUtils.encode(entity.getPassword());
		entity.setPassword(password);

		//保存用户
		entity.setSuperAdmin(SysEnum.sAdmin.NO.value());
		insert(entity);

		//保存角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());

		//保存用户岗位关系
		sysUserPostService.saveOrUpdate(entity.getId(), dto.getPostIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

		//密码加密
		if(StringUtils.isBlank(dto.getPassword())){
			entity.setPassword(null);
		}else{
			String password = PasswordUtils.encode(entity.getPassword());
			entity.setPassword(password);
		}

		//更新用户
		updateById(entity);

		//更新角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());

		//保存用户岗位关系
		sysUserPostService.saveOrUpdate(entity.getId(), dto.getPostIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateUserInfo(SysUserDTO dto) {
		SysUserEntity entity = selectById(dto.getId());
		entity.setAvatar(dto.getAvatar());
		entity.setRealName(dto.getRealName());
		entity.setGender(dto.getGender());
		entity.setMobile(dto.getMobile());
		entity.setEmail(dto.getEmail());

		updateById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long[] ids) {
		//删除用户
		baseDao.deleteBatchIds(Arrays.asList(ids));

		//删除角色用户关系
		sysRoleUserService.deleteByUserIds(ids);

		//删除用户岗位关系
		sysUserPostService.deleteByUserIds(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePassword(Long id, String newPassword) {
		newPassword = PasswordUtils.encode(newPassword);

		baseDao.updatePassword(id, newPassword);
	}

	@Override
	public int getCountByDeptId(Long deptId) {
		return baseDao.getCountByDeptId(deptId);
	}

	@Override
	public List<Long> getUserIdListByDeptId(List<Long> deptIdList) {
		return baseDao.getUserIdListByDeptId(deptIdList);
	}

}