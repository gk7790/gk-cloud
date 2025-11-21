package com.gk.common.beans;

public interface CurrentUser {

    /**
     * 获取当前用户ID
     */
    Long getUserId();

    /**
     * 获取当前用户名
     */
    String getUsername();

    /**
     * 获取当前部门ID
     */
    Long getDeptId();

    /**
     * 获取当前租户ID
     */
    Long getTenantId();

    /**
     * 是否是管理员
     */
    boolean isAdmin();

}
