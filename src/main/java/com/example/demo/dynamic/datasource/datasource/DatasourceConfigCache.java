package com.example.demo.dynamic.datasource.datasource;

import com.example.demo.dynamic.datasource.modle.DatasourceConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sxc
 * @version 1.0
 * description:
 * @date 2020/5/5 9:35
 */
public enum DatasourceConfigCache {
    /**
     * 当前实例
     */
    INSTANCE;

    /**
     * 管理动态数据源列表。
     */
    private static final Map<Long, DatasourceConfig> CONFIG_CACHE = new ConcurrentHashMap<>();

    /**
     * 添加数据源配置
     *
     * @param id     数据源配置id
     * @param config 数据源配置
     */
    public synchronized void addConfig(Long id, DatasourceConfig config) {
        CONFIG_CACHE.put(id, config);
    }

    /**
     * 查询数据源配置
     *
     * @param id 数据源配置id
     * @return 数据源配置
     */
    public synchronized DatasourceConfig getConfig(Long id) {
        if (CONFIG_CACHE.containsKey(id)) {
            return CONFIG_CACHE.get(id);
        }
        return null;
    }

    /**
     * 清除数据源配置
     */
    public synchronized void removeConfig(Long id) {
        CONFIG_CACHE.remove(id);
        // 同步清除 DatasourceHolder 对应的数据源
        DatasourceHolder.INSTANCE.removeDatasource(id);
    }
}
