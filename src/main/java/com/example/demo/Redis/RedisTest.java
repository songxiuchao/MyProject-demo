package com.example.demo.Redis;

import com.example.demo.Redis.config.RedisUtil;
import com.example.demo.Redis.po.User;
import com.example.demo.Redis.service.RedisService;
import com.example.demo.Redis.utils.ObjectUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: demo
 * @description: redis测试类
 * @author: xiuchao Song
 * @create: 2019-11-02 16:15
 **/
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisTest {
    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;

    /**
     * 查询用户信息放入redis里面
     * @return
     */

    @ApiOperation(value = "获取用户信息" ,  notes="获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "参数名称", value = "意义", dataType = "参数类型",required = false)
    })
    @PostMapping("/getUserInfo")
    public void getUserInfo(){
        User userInfo = redisService.getUserInfo();
        if(ObjectUtils.notEmpty(userInfo)){
            redisUtil.set("redis-name",userInfo.getName());
        }
        String username = redisUtil.get("redis-name");
        System.out.println(username);
    }
}
