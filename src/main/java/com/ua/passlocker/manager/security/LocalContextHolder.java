package com.ua.passlocker.manager.security;

import com.ua.passlocker.manager.models.ContextHolder;

public class LocalContextHolder {

    private static ThreadLocal<ContextHolder> threadLocal = new ThreadLocal<>();

    public static ContextHolder getContextHolder() {
        return threadLocal.get();
    }

    public static void removeContextHolder() {
        threadLocal.remove();
    }

    public static void setContextHolder(ContextHolder contextHolder) {
        threadLocal.set(contextHolder);
    }
}
