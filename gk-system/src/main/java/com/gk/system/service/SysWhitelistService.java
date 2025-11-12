package com.gk.system.service;


import com.gk.common.core.service.CrudService;
import com.gk.system.dto.SysWhitelistDTO;
import com.gk.system.entity.SysWhitelistEntity;

/**
 * 白名单
 *
 * @author stark system@gmail.com
 * @since 1.0.0 2019-05-08
 */
public interface SysWhitelistService extends CrudService<SysWhitelistEntity, SysWhitelistDTO> {
    void save(SysWhitelistDTO dto);

    void delete(Long[] ids);

    boolean isAccessAllowed(String ip);

}