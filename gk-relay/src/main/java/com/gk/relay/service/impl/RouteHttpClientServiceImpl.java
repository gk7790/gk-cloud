package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.constant.Constant;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.RouteHttpClientDao;
import com.gk.relay.dto.RouteHttpClientDTO;
import com.gk.relay.entity.RouteHttpClientEntity;
import com.gk.relay.service.RouteHttpClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * sing-box远程规则集HTTP Client配置表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
public class RouteHttpClientServiceImpl extends CrudServiceImpl<RouteHttpClientDao, RouteHttpClientEntity, RouteHttpClientDTO> implements RouteHttpClientService {

    @Override
    public QueryWrapper<RouteHttpClientEntity> getWrapper(DataMap params){
        QueryWrapper<RouteHttpClientEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


}