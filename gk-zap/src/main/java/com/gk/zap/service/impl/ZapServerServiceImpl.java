package com.gk.zap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.constant.Constant;
import com.gk.common.tools.DataMap;
import com.gk.zap.dao.ZapServerDao;
import com.gk.zap.dto.ZapServerDTO;
import com.gk.zap.entity.ZapServerEntity;
import com.gk.zap.service.ZapServerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * Zap服务器
 *
 * @author Lowen lowen@gmail.com
 * @since 3.0 2026-01-05
 */
@Service
public class ZapServerServiceImpl extends CrudServiceImpl<ZapServerDao, ZapServerEntity, ZapServerDTO> implements ZapServerService {

    @Override
    public QueryWrapper<ZapServerEntity> getWrapper(DataMap params){
        QueryWrapper<ZapServerEntity> wrapper = new QueryWrapper<>();


        return wrapper;
    }


}