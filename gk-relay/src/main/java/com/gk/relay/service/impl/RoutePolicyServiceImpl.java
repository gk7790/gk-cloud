package com.gk.relay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.constant.Constant;
import com.gk.common.tools.DataMap;
import com.gk.relay.dao.RoutePolicyDao;
import com.gk.relay.dto.RoutePolicyDTO;
import com.gk.relay.entity.RoutePolicyEntity;
import com.gk.relay.service.RoutePolicyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * sing-box路由策略表
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-04-30
 */
@Service
public class RoutePolicyServiceImpl extends CrudServiceImpl<RoutePolicyDao, RoutePolicyEntity, RoutePolicyDTO> implements RoutePolicyService {

    @Override
    public QueryWrapper<RoutePolicyEntity> getWrapper(DataMap params){
        QueryWrapper<RoutePolicyEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


}