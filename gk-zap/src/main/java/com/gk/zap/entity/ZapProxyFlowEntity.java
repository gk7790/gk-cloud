package com.gk.zap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zap_proxy_flow")
public class ZapProxyFlowEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 代理id
     */
    private Long proxyId;
    /**
     * 客户端id
     */
    private Long clientId;
    /**
     * 服务器Id
     */
    private Long serverId;
    /**
     * 协议
     */
    private String protocol;
    /**
     * 请求流量
     */
    private Integer reqBytes;
    /**
     * 响应流量
     */
    private Integer respBytes;
    /**
     * 总流量
     */
    private Integer totalBytes;
    /**
     * 耗时
     */
    private Integer duration;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

}
