package com.gk.common.interceptor;

import com.gk.common.beans.CurrentUser;
import com.gk.common.utils.ValueUtils;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FieldMetaObjectHandler extends MysqlMetaObjectHandler {
    private final CurrentUser currentUser;

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = ValueUtils.defaultValue(currentUser.getUserId(), 0L);
        Long deptId = ValueUtils.defaultValue(currentUser.getDeptId(), 0L);
        Long tenantId = ValueUtils.defaultValue(currentUser.getTenantId(), 0L);

        //创建者
        strictInsertFill(metaObject, CREATED_BY, Long.class, userId);
        //创建时间
        strictInsertFill(metaObject, CREATED_AT, LocalDateTime.class, LocalDateTime.now());

        //创建者所属部门
        strictInsertFill(metaObject, DEPT_ID, Long.class, deptId);

        //创建者所属部门
        strictInsertFill(metaObject, TENANT_ID, Long.class, tenantId);

        //更新者
        strictInsertFill(metaObject, UPDATED_BY, Long.class, userId);
        //更新时间
        strictInsertFill(metaObject, UPDATED_AT, LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = ValueUtils.defaultValue(currentUser.getUserId(), 0L);
        //更新者
        strictUpdateFill(metaObject, UPDATED_BY, Long.class, userId);
        //更新时间
        strictUpdateFill(metaObject, UPDATED_AT, LocalDateTime.class, LocalDateTime.now());
    }
}
