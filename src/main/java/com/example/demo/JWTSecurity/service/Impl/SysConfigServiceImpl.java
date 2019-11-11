package com.example.demo.JWTSecurity.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.JWTSecurity.dao.SysConfigMapper;
import com.example.demo.JWTSecurity.po.SysConfig;
import com.example.demo.JWTSecurity.service.SysConfigService;
import org.springframework.stereotype.Service;

/**
 * @program: 参数配置 服务实现类
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-07 09:26
 **/
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
}
