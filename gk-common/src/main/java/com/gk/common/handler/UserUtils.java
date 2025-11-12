package com.gk.common.handler;

public class UserUtils {
    /** 获取当前登录用户 */
    public static LoginUser getCurrentUser() {
        return LoginUserHolder.get();
    }

    /** 获取用户ID */
    public static Long getUserId() {
        LoginUser user = getCurrentUser();
        return user != null ? user.getUserId() : null;
    }

    /** 获取用户名 */
    public static String getUsername() {
        LoginUser user = getCurrentUser();
        return user != null ? user.getUsername() : null;
    }

    /** 获取角色 */
    public static java.util.List<String> getRoles() {
        LoginUser user = getCurrentUser();
        return user != null ? user.getRoles() : java.util.Collections.emptyList();
    }
}
