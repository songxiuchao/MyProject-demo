package com.example.demo.dynamic.datasource.mapper;

import com.example.demo.dynamic.datasource.config.MyMapper;
import com.example.demo.dynamic.datasource.modle.DatasourceConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sxc
 * @version 1.0
 * @date 2020/5/5 8:31
 */
@Mapper
public interface DatasourceConfigMapper extends MyMapper<DatasourceConfig> {
}
