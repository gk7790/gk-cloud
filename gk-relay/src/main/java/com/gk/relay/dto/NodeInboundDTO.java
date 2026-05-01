package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* Relay节点入站配置表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-05-01
*/
@Data
@Schema(title = "Relay节点入站配置表")
public class NodeInboundDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "节点ID")
    private Long nodeId;
    @Schema(title = "协议：vless、trojan、shadowsocks、hysteria2、tuic、socks、mixed、vmess")
    private String protocol;
    @Schema(title = "是否启用")
    private Integer enabled;
    @Schema(title = "入站名称")
    private String inboundName;
    @Schema(title = "sing-box inbound tag，例如 vless-reality-in")
    private String inboundTag;
    @Schema(title = "监听地址，例如 ::、0.0.0.0、127.0.0.1")
    private String listenAddr;
    @Schema(title = "监听端口")
    private Integer listenPort;
    @Schema(title = "传输类型：tcp、ws、grpc、httpupgrade、quic")
    private String transportType;
    @Schema(title = "网络类型")
    private String network;
    @Schema(title = "VLESS flow，例如 xtls-rprx-vision")
    private String flow;
    @Schema(title = "是否启用TLS")
    private Integer tlsEnabled;
    @Schema(title = "TLS模式：none、tls、reality")
    private String tlsMode;
    @Schema(title = "是否启用sniff")
    private Integer sniffEnabled;
    @Schema(title = "sniff是否覆盖目标地址")
    private Integer sniffOverrideDestination;
    @Schema(title = "是否启用UDP")
    private Integer udpEnabled;
    @Schema(title = "是否启用TCP Fast Open")
    private Integer tcpFastOpen;
    @Schema(title = "协议扩展配置JSON，例如 method、password、obfs、congestion_control")
    private String protocolOptions;
    @Schema(title = "传输扩展配置JSON，例如 ws path、headers、grpc service_name")
    private String transportOptions;
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