package com.userzrq.aop;

import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        // 用当前线程的引用去存值
        CONTEXT_HOLDER.set(dataSource);
    }

    public static String getDataSource() {
        // 用当前线程的引用去取值
        return CONTEXT_HOLDER.get();
    }

    public static void cleanDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
