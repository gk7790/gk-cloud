package com.gk.system.web;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.gk.common.annotation.RequiresPermission;
import com.gk.common.enums.SysEnum;
import com.gk.common.exception.ErrorCode;
import com.gk.common.handler.LoginUser;
import com.gk.common.handler.UserUtils;
import com.gk.common.tools.R;
import com.gk.common.utils.ConvertUtils;
import com.gk.common.validator.AssertUtils;
import com.gk.system.dto.SysMenuDTO;
import com.gk.system.entity.SysMenuEntity;
import com.gk.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 * 
 * @author Lowen
 */
@RestController
@RequestMapping("/sys/menu")
@Tag(name = "菜单管理")
@AllArgsConstructor
public class SysMenuController {
	private final SysMenuService sysMenuService;

	@GetMapping("nav")
	@Operation(summary = "导航")
	public R<?> nav(){
        LoginUser user = UserUtils.getCurrentUser();
		List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, SysEnum.MenuTypeEnum.MENU.value());
		return R.ok(list);
	}

	@GetMapping("permissions")
	@Operation(summary = "权限标识")
	public R<?> permissions(){
        LoginUser user = UserUtils.getCurrentUser();
		Set<String> set = sysMenuService.getUserPermissions(user);
		return R.ok(set);
	}

	@GetMapping("list")
	@Operation(summary = "列表")
	@Parameter(name = "type", description = "菜单类型 0：菜单 1：按钮  null：全部", in = ParameterIn.QUERY)
	public R<?> list(Integer type){
		List<SysMenuDTO> list = sysMenuService.getAllMenuList(type);
		return R.ok(list);
	}

	@GetMapping("{id}")
	@Operation(summary = "信息")
	@RequiresPermission("sys:menu:info")
	public R<?> get(@PathVariable("id") Long id){
		SysMenuDTO data = sysMenuService.get(id);
		return R.ok(data);
	}

    @PutMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermission("sys:menu:info")
    public R<?> updateById(@PathVariable("id") Long id, @RequestBody SysMenuDTO dto){
        dto.setId(id);
        sysMenuService.update(dto);
        return R.ok();
    }

	@PostMapping
	@Operation(summary = "保存")
	@RequiresPermission("sys:menu:save")
	public R<?> save(@RequestBody SysMenuDTO dto){
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);
		//效验数据
		sysMenuService.addMenu(entity);
		return R.ok();
	}

	@PutMapping
	@Operation(summary = "修改")
	@RequiresPermission("sys:menu:update")
	public R<?> update(@RequestBody SysMenuDTO dto){
		sysMenuService.update(dto);
		return R.ok();
	}

	@DeleteMapping("{id}")
	@Operation(summary = "删除")
	@RequiresPermission("sys:menu:delete")
	public R<?> delete(@PathVariable("id") Long id){
		//效验数据
		AssertUtils.isNull(id, "id");
		//判断是否有子菜单或按钮
		List<SysMenuDTO> list = sysMenuService.getListPid(id);
		if(list.size() > 0){
			return R.error(ErrorCode.SUB_MENU_EXIST);
		}
		sysMenuService.delete(id);
		return R.ok();
	}

	@GetMapping("select")
	@Operation(summary = "角色菜单权限")
	@RequiresPermission("sys:menu:select")
	public R<?> select(){
        LoginUser user = UserUtils.getCurrentUser();
		List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, null);
		return R.ok(list);
	}
}