package com.example.demo.dynamic.datasource;

import com.example.demo.dynamic.datasource.datasource.DatasourceConfigCache;
import com.example.demo.dynamic.datasource.datasource.DatasourceConfigContextHolder;
import com.example.demo.dynamic.datasource.mapper.DatasourceConfigMapper;
import com.example.demo.dynamic.datasource.modle.DatasourceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final DatasourceConfigMapper configMapper;

    @Override
    public void run(String... args) throws Exception {
        // 设置默认的数据源
        DatasourceConfigContextHolder.setDefaultDatasource();
        // 查询所有数据库配置列表
        List<DatasourceConfig> datasourceConfigs = configMapper.selectAll();
        System.out.println("加载其余数据源配置列表: " + datasourceConfigs);
        // 将数据库配置加入缓存
        datasourceConfigs.forEach(config -> DatasourceConfigCache.INSTANCE.addConfig(config.getId(), config));
    }
}
