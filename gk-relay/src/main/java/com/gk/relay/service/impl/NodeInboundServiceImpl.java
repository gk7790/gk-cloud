package com.gk.relay.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.constant.Constant;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.NodeInboundDao;
import com.gk.relay.dto.NodeInboundDTO;
import com.gk.relay.entity.NodeEntity;
import com.gk.relay.entity.NodeInboundEntity;
import com.gk.relay.service.NodeInboundService;
import com.gk.relay.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Relay节点入站配置表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-05-01
 */
@Service
@RequiredArgsConstructor
public class NodeInboundServiceImpl extends CrudServiceImpl<NodeInboundDao, NodeInboundEntity, NodeInboundDTO> implements NodeInboundService {

    private final NodeService nodeService;

    @Override
    public QueryWrapper<NodeInboundEntity> getWrapper(DataMap params){
        QueryWrapper<NodeInboundEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }

    public List<NodeInboundEntity> listNodeInboundByNodeIds(List<Long> nodeIdList){
        QueryWrapper<NodeInboundEntity> wrapper = new QueryWrapper<>();
        wrapper.in("node_id",nodeIdList);
        wrapper.eq("enabled", Constant.Enabled.ENABLE.getValue());
        wrapper.eq("deleted", Constant.Deleted.NORMAL.getValue());
        return baseDao.selectList(wrapper);
    }


    @Override
    public List<JSONObject> getClientConfigByNodeId(List<Long> nodeIdList) {
        List<JSONObject> outbounds = new ArrayList<>();
        List<NodeInboundEntity> nodeInboundList = listNodeInboundByNodeIds(nodeIdList);
        for (NodeInboundEntity inbound : nodeInboundList) {
            NodeEntity node = nodeService.selectById(inbound.getNodeId());
            JSONObject nodeProxy = new JSONObject();

            nodeProxy.put("type", inbound.getProtocol());
            nodeProxy.put("tag", node.getNodeCode());
            nodeProxy.put("server", node.getServerHost());
            nodeProxy.put("server_port", inbound.getListenPort());
            nodeProxy.put("uuid", "");
            if (StringUtils.isNotBlank(inbound.getFlow())) {
                nodeProxy.put("flow", inbound.getFlow());
            }
            nodeProxy.put("packet_encoding", "xudp");
            nodeProxy.put("tls", "");

            outbounds.add(nodeProxy);
        }
        return outbounds;
    }
}