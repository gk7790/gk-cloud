package com.gk.system.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gk.common.beans.SysParamsRedis;
import com.gk.common.constant.Constant;
import com.gk.common.core.service.impl.BaseServiceImpl;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.dto.LabelDTO;
import com.gk.common.exception.ErrorCode;
import com.gk.common.exception.GkException;
import com.gk.common.page.PageData;
import com.gk.common.tools.DataMap;
import com.gk.common.utils.ConvertUtils;
import com.gk.system.dao.SysParamsDao;
import com.gk.system.dao.SysTenantDao;
import com.gk.system.dto.SysParamsDTO;
import com.gk.system.dto.SysTenantDTO;
import com.gk.system.entity.SysParamsEntity;
import com.gk.system.entity.SysTenantEntity;
import com.gk.system.service.SysParamsService;
import com.gk.system.service.SysTenantService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 参数管理
 *
 * @author Lowen
 * @since 1.0.0
 */
@Service
public class SysTenantServiceImpl extends CrudServiceImpl<SysTenantDao, SysTenantEntity, SysTenantDTO> implements SysTenantService {
    private final SysParamsRedis sysParamsRedis;

    protected SysTenantServiceImpl(SysTenantDao baseDao, SysParamsRedis sysParamsRedis) {
        super(baseDao);
        this.sysParamsRedis = sysParamsRedis;
    }

    @Override
    public QueryWrapper<SysTenantEntity> getWrapper(DataMap params) {
        QueryWrapper<SysTenantEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    public List<LabelDTO> getDict(DataMap params) {
        List<Integer> list = params.getList("status", Integer.class, Constant.Status.defaultStatus());

        QueryWrapper<SysTenantEntity> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name");
        wrapper.in("status", list);
        List<SysTenantEntity> result = baseDao.selectList(wrapper);

        return result.stream().map(item -> new LabelDTO(item.getId(), item.getName())).toList();
    }
}