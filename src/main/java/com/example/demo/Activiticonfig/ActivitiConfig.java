package com.example.demo.Activiticonfig;

import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @program: demo
 * @description: ActivitiConfig配之类
 * @author: xiuchao Song
 * @create: 2019-11-15 14:46
 **/
//@Configuration //需要的时候打开
public class ActivitiConfig {
    /*
     * 配置分为以下几步骤
     * 1. 创建ActivitiConfig
     * 2. 使用ActivitiConfig创建ProcessEngineFactoryBean
     * 3. 使用ProcessEngineFactoryBean创建ProcessEngine对象
     * 4. 使用ProcessEngine对象创建需要的服务对象
     * */

    private final DataSource dataSource;

    private final PlatformTransactionManager platformTransactionManager;
    @Autowired
    public ActivitiConfig(DataSource dataSource, PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.platformTransactionManager = platformTransactionManager;
    }
    /*
     * 1. 创建配置文件，也就是提供一些配置信息，这样就可以自定义自己的创建信息了
     * 需要一些参数，1. 数据源。2. 事务管理器。
     * 这里还加入了自动扫描process包下的bpmn(流程定义文件)的设置，这样就可以省去了部署
     * */
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        spec.setDataSource(dataSource);
        spec.setTransactionManager(platformTransactionManager);
        spec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        Resource[] resources = null;
        // 启动自动部署流程
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath*:process/*.bpmn");
        } catch (IOException e) {
            e.printStackTrace();
        }
        spec.setDeploymentResources(resources);
        return spec;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine() {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration());
        return processEngineFactoryBean;
    }

    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngine().getObject().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngine().getObject().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngine().getObject().getTaskService();
    }

    @Bean
    public HistoryService historyService() throws Exception {
        return processEngine().getObject().getHistoryService();
    }
}