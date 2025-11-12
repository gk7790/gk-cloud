package com.gk.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gk.common.constant.Constant;
import com.gk.common.core.service.impl.CrudServiceImpl;
import com.gk.common.exception.GkException;
import com.gk.common.page.PageData;
import com.gk.common.redis.RedisKeys;
import com.gk.common.redis.RedisUtils;
import com.gk.common.tools.DataMap;
import com.gk.common.utils.ConvertUtils;
import com.gk.common.utils.IpUtils;
import com.gk.system.dao.SysWhitelistDao;
import com.gk.system.dto.SysWhitelistDTO;
import com.gk.system.entity.SysWhitelistEntity;
import com.gk.system.service.SysWhitelistService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 白名单
 *
 * @author stark system@gmail.com
 * @since 1.0.0 2019-05-08
 */
@Service
public class SysWhitelistServiceImpl extends CrudServiceImpl<SysWhitelistDao, SysWhitelistEntity, SysWhitelistDTO> implements SysWhitelistService {
    @Autowired
    private RedisUtils redisUtils;

    protected SysWhitelistServiceImpl(SysWhitelistDao baseDao) {
        super(baseDao);
    }

    @Override
    public QueryWrapper<SysWhitelistEntity> getWrapper(DataMap params) {
        String id = (String) params.get("id");

        QueryWrapper<SysWhitelistEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<SysWhitelistDTO> page(DataMap params) {
        IPage<SysWhitelistEntity> page = getPage(params, Constant.CREATED_AT, false);
        List<SysWhitelistEntity> list = baseDao.getList(params);
        return getPageData(list, page.getTotal(), currentDtoClass());
    }

    @Override
    public void save(SysWhitelistDTO dto) {
        String allowIp = dto.getAllowIp().trim();
        if (allowIp.contains("*")) {
            allowIp = IpUtils.getAllowIpRange(allowIp);
            dto.setAllowIp(allowIp);
        }
//        boolean isPass = IpUtils.validate(allowIp);
//        if (!isPass) {
//            throw new RenException(allowIp + " IP格式不正确,注意是否包含空格！");
//        }
        boolean isExists = selectExists(allowIp);
        if (isExists) {
            throw new GkException(dto.getAllowIp() + " 已存在");
        }
        // 获取IP归属地
       /* try {
            dto.setIpSource(IpUtils.getRegion(dto.getAllowIp()));
        } catch (Exception e) {
        }*/
        SysWhitelistEntity entity = ConvertUtils.sourceToTarget(dto, currentModelClass());
        int result = baseDao.insert(entity);
        if (result > 0) {
            redisUtils.delete(RedisKeys.SYS_WHITE_LIST_KEY);
        }
    }

    @Override
    public void delete(Long[] ids) {
        int result = baseDao.deleteBatchIds(Arrays.asList(ids));
        if (result > 0) {
            redisUtils.delete(RedisKeys.SYS_WHITE_LIST_KEY);
        }
    }

    @Override
    public boolean isAccessAllowed(String ip) {
        Set<String> ipList = new HashSet<>();
        Set<String> objSet = redisUtils.getSet(RedisKeys.SYS_WHITE_LIST_KEY, String.class);
        if (CollectionUtils.isNotEmpty(objSet)) {
            ipList = objSet;
        } else {
            QueryWrapper<SysWhitelistEntity> wrapper = new QueryWrapper<>();
            List<SysWhitelistEntity> list = baseDao.selectList(wrapper);
            if (CollectionUtil.isNotEmpty(list)) {
                ipList = list.stream().map(SysWhitelistEntity::getAllowIp).collect(Collectors.toSet());
                redisUtils.addSet(RedisKeys.SYS_WHITE_LIST_KEY, ipList);
            }
        }
        return IpUtils.checkContainIp(ip, ipList);
    }

    /**
     * 校验是否重复
     * @param allowIp
     * @return
     */
    public boolean selectExists(String allowIp) {
        QueryWrapper<SysWhitelistEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("allow_ip", allowIp);
        return baseDao.selectCount(wrapper) > 0;
    }
}