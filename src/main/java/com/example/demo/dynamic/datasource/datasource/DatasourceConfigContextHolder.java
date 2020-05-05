package com.example.demo.dynamic.datasource.datasource;

/**
 * @author sxc
 * @version 1.0
 * @date 2020/5/5 8:36
 * description:数据源标识管理
 */
public class DatasourceConfigContextHolder {
    private static final ThreadLocal<Long> DATASOURCE_HOLDER = ThreadLocal.withInitial(() -> DatasourceHolder.DEFAULT_ID);

    /**
     * 设置默认数据源
     */
    public static void setDefaultDatasource() {
        DATASOURCE_HOLDER.remove();
        setCurrentDatasourceConfig(DatasourceHolder.DEFAULT_ID);
    }

    /**
     * 获取当前数据源配置id
     *
     * @return 数据源配置id
     */
    public static Long getCurrentDatasourceConfig() {
        return DATASOURCE_HOLDER.get();
    }

    /**
     * 设置当前数据源配置id
     *
     * @param id 数据源配置id
     */
    public static void setCurrentDatasourceConfig(Long id) {
        DATASOURCE_HOLDER.set(id);
    }

}
