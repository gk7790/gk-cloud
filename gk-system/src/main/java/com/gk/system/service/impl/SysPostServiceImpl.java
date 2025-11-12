
package com.gk.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.tools.DataMap;
import com.gk.common.utils.ConvertUtils;
import com.gk.system.dao.SysPostDao;
import com.gk.system.dto.SysPostDTO;
import com.gk.system.entity.SysPostEntity;
import com.gk.system.service.SysPostService;
import com.gk.system.service.SysUserPostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 岗位管理
 *
 * @author Lowen
 */
@Service
public class SysPostServiceImpl extends CrudServiceImpl<SysPostDao, SysPostEntity, SysPostDTO> implements SysPostService {
    @Autowired
    private SysUserPostService sysUserPostService;

    protected SysPostServiceImpl(SysPostDao baseDao) {
        super(baseDao);
    }


    @Override
    public QueryWrapper<SysPostEntity> getWrapper(DataMap params){
        QueryWrapper<SysPostEntity> wrapper = new QueryWrapper<>();

        String postCode = (String)params.get("postCode");
        wrapper.like(StringUtils.isNotBlank(postCode), "post_code", postCode);

        String postName = (String)params.get("postName");
        wrapper.like(StringUtils.isNotBlank(postName), "post_name", postName);

        String status = (String)params.get("status");
        if(StringUtils.isNotBlank(status)){
            wrapper.eq("status", Integer.parseInt(status));
        }

        wrapper.orderByAsc("sort");

        return wrapper;
    }

    @Override
    public List<SysPostDTO> list(DataMap params) {
        List<SysPostEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysPostDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除岗位
        baseDao.deleteBatchIds(Arrays.asList(ids));

        //删除岗位用户关系
        sysUserPostService.deleteByPostIds(ids);
    }
}