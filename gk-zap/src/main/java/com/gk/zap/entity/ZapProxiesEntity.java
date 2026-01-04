package com.gk.zap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zap_proxies")
public class ZapProxiesEntity extends SimpleEntity {
    /**
     * 客户端id
     */
    private Long clientId;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 协议类型
     */
    private String protocol;
    /**
     * 本地地址
     */
    private String localAddr;
    /**
     * 本地端口
     */
    private Integer localPort;
    /**
     * 绑定地址
     */
    private String bindAddr;
    /**
     * 绑定端口
     */
    private Integer bindPort;
    /**
     * 池子数量
     */
    private Integer poolCount;
    /**
     * 备注
     */
    private String remark;
}
