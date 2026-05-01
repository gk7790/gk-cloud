package com.gk.relay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* sing-box规则集表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Data
@Schema(title = "sing-box规则集表")
public class RouteRuleSetDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private Long id;
    @Schema(title = "规则集名称")
    private String ruleSetName;
    @Schema(title = "规则集tag，对应sing-box rule_set tag")
    private String ruleSetTag;
    @Schema(title = "规则集类型：local/remote/inline")
    private String setType;
    @Schema(title = "格式：binary/source；local/remote使用")
    private String formatType;
    @Schema(title = "分类：geosite/geoip/custom/ad/private/reject等")
    private String category;
    @Schema(title = "数据类型：domain/ip/mixed/process/other")
    private String dataKind;
    @Schema(title = "local规则集路径，例如 rules/geosite-google.srs")
    private String path;
    @Schema(title = "remote规则集URL，例如 https://xxx/xxx.srs")
    private String url;
    @Schema(title = "remote规则集更新间隔，例如 1d/12h/7d")
    private String updateInterval;
    @Schema(title = "remote规则集下载使用的http_client tag")
    private String httpClientTag;
    @Schema(title = "inline规则集内容，对应 rules[]")
    private String inlineRules;
    @Schema(title = "规则数量，可选统计")
    private Integer ruleCount;
    @Schema(title = "规则文件hash，用于校验版本")
    private String fileHash;
    @Schema(title = "规则集版本号")
    private Long versionNo;
    @Schema(title = "是否启用：0否 1是")
    private Integer enabled;
    @Schema(title = "是否系统内置：0否 1是")
    private Integer systemBuiltin;
    @Schema(title = "是否允许编辑：0否 1是")
    private Integer editable;
    @Schema(title = "规则来源名称")
    private String sourceName;
    @Schema(title = "备注")
    private String remark;
    @Schema(title = "完整rule_set JSON；不为空时优先使用")
    private String rawJson;
    @Schema(title = "扩展字段")
    private String extraJson;
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