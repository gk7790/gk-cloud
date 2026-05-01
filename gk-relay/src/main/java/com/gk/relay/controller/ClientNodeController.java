package com.gk.relay.controller;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSONObject;
import com.gk.common.annotation.RequestMap;
import com.gk.common.beans.CurrentUser;
import com.gk.common.dto.AuthUser;
import com.gk.common.exception.ErrorCode;
import com.gk.common.tools.DataMap;
import com.gk.common.tools.R;
import com.gk.common.tools.Result;
import com.gk.relay.dto.NodeDTO;
import com.gk.relay.entity.RelayGroupEntity;
import com.gk.relay.service.ClientService;
import com.gk.relay.service.NodeService;
import com.gk.relay.service.RelayGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
* relay客户表
*
* @author lowen lowen@gmail.com
* @since 3.0 2026-04-28
*/
@RestController
@RequestMapping("relay/client-node")
@Tag(name = "Relay客户表")
@RequiredArgsConstructor
public class ClientNodeController {
    private final ClientService clientService;
    private final NodeService nodeService;
    private final CurrentUser currentUser;
    private final RelayGroupService groupService;

    /**
     * 获取客户端接口列表
     */
    @GetMapping("list")
    @Operation(summary = "登入")
    public R<?> getList(@RequestMap DataMap params){
        //普通管理员，只能查询所属部门及子部门的数据
        AuthUser user = currentUser.getAuthUser();
        List<NodeDTO> list = nodeService.getNodeByClient(user);
        return R.ok(list);
    }

    /**
     * 获取节点
     * @param params 节点id
     * @return
     */
    @GetMapping("config")
    @Operation(summary = "信息")
    public R<?> getNodeConfig(@RequestMap DataMap params){
        Long nodeId = params.getLong("nodeId", 0L);
        if (nodeId <= 0 ) {
            return R.error(ErrorCode.BAD_REQUEST, "nodeId");
        }
        AuthUser user = currentUser.getAuthUser();

        RelayGroupEntity group = groupService.getByClientId(user.getId());
        if (ObjUtil.isEmpty(group)) {
            return R.error(ErrorCode.BAD_REQUEST, "Unassigned group");
        }

        Result<JSONObject> result = groupService.getNodeConfig(group.getId(), nodeId, group.getRoutePolicyId());
        return R.ok(result.getData());
    }

    /**
     * 获取节点
     * @param params 节点id
     * @return
     */
    @GetMapping("config1")
    @Operation(summary = "信息")
    public R<?> getNode(@RequestMap DataMap params){
        String nodeJson = """
                {
                  "log": {
                    "level": "info",
                    "timestamp": true
                  },
                  "inbounds": [
                    {
                      "type": "mixed",
                      "tag": "mixed-in",
                      "listen": "127.0.0.1",
                      "listen_port": 10808
                    }
                  ],
                  "outbounds": [
                    {
                      "type": "vless",
                      "tag": "proxy",
                      "server": "154.17.21.142",
                      "server_port": 443,
                      "uuid": "ea3405b8-e902-4adc-8740-b65593df07cf",
                      "flow": "xtls-rprx-vision",
                      "packet_encoding": "xudp",
                      "tls": {
                        "enabled": true,
                        "server_name": "www.cloudflare.com",
                        "utls": {
                          "enabled": true,
                          "fingerprint": "chrome"
                        },
                        "reality": {
                          "enabled": true,
                          "public_key": "Kzi5-V5tIqbLb0jNzm-4ZBJgEPPWHJnE-oaJqj9d8kU",
                          "short_id": "6ba85179e30d4fc2"
                        }
                      }
                    }
                  ],
                  "route": {
                    "rules": [
                      {
                        "action": "sniff"
                      }
                    ],
                    "final": "proxy",
                    "auto_detect_interface": true
                  }
                }
                """;
        return R.ok(JSONObject.parseObject(nodeJson));
    }

}