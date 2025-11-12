package com.gk.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gk.common.core.service.impl.BaseServiceImpl;
import com.gk.common.page.PageData;
import com.gk.common.tools.DataMap;
import com.gk.common.utils.ConvertUtils;
import com.gk.common.utils.HttpContextUtils;
import com.gk.system.dao.SysDictDataDao;
import com.gk.system.dao.SysDictTypeDao;
import com.gk.system.dto.SysDictTypeDTO;
import com.gk.system.entity.DictData;
import com.gk.system.entity.DictType;
import com.gk.system.entity.SysDictTypeEntity;
import com.gk.system.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 字典类型
 *
 * @author Lowen
 */
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {
    @Autowired
    private SysDictDataDao sysDictDataDao;

    protected SysDictTypeServiceImpl(SysDictTypeDao baseDao) {
        super(baseDao);
    }

    @Override
    public PageData<SysDictTypeDTO> page(DataMap params) {
        IPage<SysDictTypeEntity> page = baseDao.selectPage(
            getPage(params, "sort", true),
            getWrapper(params)
        );

        return getPageData(page, SysDictTypeDTO.class);
    }

    private QueryWrapper<SysDictTypeEntity> getWrapper(DataMap params){
        String dictType = (String) params.get("dictType");
        String dictName = (String) params.get("dictName");

        QueryWrapper<SysDictTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(dictType), "dict_type", dictType);
        wrapper.like(StringUtils.isNotBlank(dictName), "dict_name", dictName);

        return wrapper;
    }


    public PageData<SysDictTypeDTO> getPage(DataMap params) {
        IPage<SysDictTypeEntity> page = baseDao.selectPage(
                getPage(params, "sort", true),
                getWrapper(params)
        );
        return getPageData(page, SysDictTypeDTO.class);
    }


    @Override
    public SysDictTypeDTO get(Long id) {
        SysDictTypeEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, SysDictTypeDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictTypeDTO dto) {
        SysDictTypeEntity entity = ConvertUtils.sourceToTarget(dto, SysDictTypeEntity.class);

        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictTypeDTO dto) {
        SysDictTypeEntity entity = ConvertUtils.sourceToTarget(dto, SysDictTypeEntity.class);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除
        deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<DictType> getAllList() {
        List<DictType> typeList = baseDao.getDictTypeList();
        List<DictData> dataList = sysDictDataDao.getDictDataList(HttpContextUtils.getLanguage());
        for(DictType type : typeList){
            for(DictData data : dataList){
                if(type.getId().equals(data.getDictTypeId())){
                    type.getDataList().add(data);
                }
            }
        }
        return typeList;
    }

    @Override
    public List<DictType> getDictTypeList() {
        return baseDao.getDictTypeList();
    }

}