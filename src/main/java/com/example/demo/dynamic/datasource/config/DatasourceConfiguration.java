package com.example.demo.dynamic.datasource.config;

import cn.stylefeng.roses.core.mutidatasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author sxc
 * @version 1.0
 * description: 数据源配置
 * @date 2020/5/5 9:38
 */
@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(DynamicDataSource.class);
        return dataSourceBuilder.build();
    }
}
