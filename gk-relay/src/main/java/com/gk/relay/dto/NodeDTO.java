package com.gk.relay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Relay节点主表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Data
@Schema(title = "Relay节点主表")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "租户ID，单租户默认0")
    private Long tenantId;
    @Schema(title = "节点编码，例如 HK-01、JP-01")
    private String nodeCode;
    @Schema(title = "节点名称，例如 香港01、日本01")
    private String nodeName;
    @Schema(title = "地区编码，例如 HK、JP、SG")
    private String regionCode;
    @Schema(title = "地区名称")
    private String regionName;
    @Schema(title = "国家或地区代码")
    private String countryCode;
    @Schema(title = "城市名称")
    private String cityName;
    @Schema(title = "业务状态：0禁用，1启用，2维护中，3异常")
    private Integer status;
    @Schema(title = "在线状态：0未知，1在线，2离线")
    private Integer onlineStatus;
    @Schema(title = "服务商，例如 DMIT、AWS、Vultr")
    private String provider;
    @Schema(title = "节点访问地址，IP或域名")
    private String serverHost;
    @Schema(title = "公网IPv4")
    private String publicIp;
    @Schema(title = "IPv6地址")
    private String ipv6Addr;
    @Schema(title = "SSH地址")
    private String sshHost;
    @Schema(title = "SSH端口")
    private Integer sshPort;
    @Schema(title = "sing-box日志级别：trace、debug、info、warn、error")
    private String logLevel;
    @Schema(title = "默认最终出站tag")
    private String routeFinalOutboundTag;
    @Schema(title = "节点权重，用于排序或负载")
    private Integer weight;
    @Schema(title = "排序值")
    private Integer sortOrder;
    @Schema(title = "最大客户数")
    private Integer maxClients;
    @Schema(title = "最大连接数")
    private Integer maxConnections;
    @Schema(title = "带宽限制Mbps")
    private Integer bandwidthLimitMbps;
    @Schema(title = "当前期望配置版本")
    private Long currentConfigVersion;
    @Schema(title = "agent已应用配置版本")
    private Long appliedConfigVersion;
    @Schema(title = "配置状态：0未知，1已应用，2待应用，3应用失败")
    private Integer configApplyStatus;
    @Schema(title = "最近一次配置应用失败原因")
    private String configApplyError;
    @Schema(title = "备注")
    private String remark;
    @Schema(title = "创建人ID")
    private Long createdBy;
    @Schema(title = "创建时间")
    private LocalDateTime createdAt;
    @Schema(title = "更新人ID")
    private Long updatedBy;
    @Schema(title = "更新时间")
    private LocalDateTime updatedAt;
    @Schema(title = "逻辑删除：0未删除，1已删除")
    private Integer deleted;
    @Schema(title = "乐观锁版本")
    private Integer version;

}