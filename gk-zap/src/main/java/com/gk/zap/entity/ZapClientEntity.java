package com.gk.zap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gk.common.core.entity.SimpleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zap_client")
public class ZapClientEntity extends SimpleEntity {
    /**
     * 客户端编码
     */
    private String code;
    /**
     * 名字
     */
    private String name;
    /**
     * 秘钥
     */
    private String secret;
    /**
     * 服务器地址
     */
    private String serverAddr;
    /**
     * 服务器端口
     */
    private String serverPort;
    /**
     * 池子数量
     */
    private Integer poolCount;
    /**
     * 协议类型
     */
    private String protocol;
    /**
     * 设备Id
     */
    private String deviceId;
    /**
     * 最后一次ping
     */
    private Long lastPing;
}
