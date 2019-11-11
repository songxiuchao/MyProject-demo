package com.example.demo.JWTSecurity.init;

import com.example.demo.JWTSecurity.constants.ConstantsContext;
import com.example.demo.JWTSecurity.po.SysConfig;
import com.example.demo.JWTSecurity.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: 参数配置 服务类
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-07 09:18
 **/
@Component
@Slf4j
public class SysConfigInit implements CommandLineRunner {

    @Resource
    private SysConfigService sysConfigService;

    @Override
    public void run(String... args) {

        //初始化所有的常量(查询SysConfig表的所有数据)
        List<SysConfig> list = sysConfigService.list();

        if (list != null && list.size() > 0) {
            for (SysConfig sysConfig : list) {
                ConstantsContext.putConstant(sysConfig.getCode(), sysConfig.getValue());
            }

            log.info("初始化常量" + list.size() + "条！");
        }

    }
}
