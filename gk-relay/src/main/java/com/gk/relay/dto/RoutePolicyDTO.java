package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* sing-box路由策略表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Data
@Schema(title = "sing-box路由策略表")
public class RoutePolicyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "策略名称")
    private String policyName;
    @Schema(title = "策略编码")
    private String policyCode;
    @Schema(title = "策略说明")
    private String description;
    @Schema(title = "默认出站tag：direct/proxy/block/proxy_hk等")
    private String finalOutbound;
    @Schema(title = "是否开启自动检测出口网卡：0否 1是")
    private Integer autoDetectInterface;
    @Schema(title = "route.default_http_client，远程规则集默认下载HTTP Client tag")
    private String defaultHttpClientTag;
    @Schema(title = "route.default_domain_resolver，可选")
    private String defaultDomainResolver;
    @Schema(title = "route.default_network_strategy，可选")
    private String defaultNetworkStrategy;
    @Schema(title = "route.default_interface，可选")
    private String defaultInterface;
    @Schema(title = "是否启用experimental.cache_file：0否 1是")
    private Integer cacheFileEnabled;
    @Schema(title = "experimental.cache_file.path")
    private String cacheFilePath;
    @Schema(title = "experimental.cache_file额外配置")
    private String cacheFileJson;
    @Schema(title = "是否启用：0否 1是")
    private Integer enabled;
    @Schema(title = "是否系统内置：0否 1是")
    private Integer systemBuiltin;
    @Schema(title = "是否允许编辑：0否 1是")
    private Integer editable;
    @Schema(title = "策略版本号，规则变更时递增")
    private Long versionNo;
    @Schema(title = "route级别扩展字段")
    private String extraJson;
    @Schema(title = "排序")
    private Integer sortOrder;
    @Schema(title = "逻辑删除：0否 1是")
    private Integer deleted;
    @Schema(title = "创建人")
    private Long createBy;
    @Schema(title = "更新人")
    private Long updateBy;
    @Schema(title = "创建时间")
    private LocalDateTime createTime;
    @Schema(title = "更新时间")
    private LocalDateTime updateTime;

}