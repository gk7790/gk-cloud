package com.gk.zap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import com.gk.common.core.entity.SimpleEntity;


/**
 * Zap服务器
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-01-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("zap_server")
public class ZapServerEntity extends SimpleEntity {
	private static final long serialVersionUID = 1L;

	/**
	* 服务编码
	*/
	private String code;
	/**
	* 名称
	*/
	private String name;
	/**
	* 服务ip
	*/
	private String ip;
	/**
	* 服务端口
	*/
	private String port;
	/**
	* 设备id
	*/
	private String deviceId;
	/**
	* 根路径(需要排除)
	*/
	private String rootPaths;
}