package com.gk.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gk.common.tools.TreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单管理
 *
 * @author Lowen
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "菜单管理")
public class SysMenuDTO extends TreeNode<SysMenuDTO> implements Serializable {

	@Schema(title = "id")
	private Long id;

	@Schema(title = "上级ID")
	private Long pid;

	@Schema(title = "菜单名称")
	private String name;

	@Schema(title = "菜单URL")
	private String url;

	@Schema(title = "类型  0：菜单   1：按钮")
	private Integer menuType;

	@Schema(title = "打开方式   0：内部   1：外部")
	private Integer openStyle;

	@Schema(title = "菜单图标")
	private String icon;

	@Schema(title = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
	private String authCode;

	@Schema(title = "排序")
	private Integer sort;

	@Schema(title = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime createdAt;

	@Schema(title = "上级菜单名称")
	private String parentName;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getPid() {
		return pid;
	}

	@Override
	public void setPid(Long pid) {
		this.pid = pid;
	}

}
