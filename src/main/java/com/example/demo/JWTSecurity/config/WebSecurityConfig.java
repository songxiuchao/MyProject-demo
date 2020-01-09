package com.example.demo.JWTSecurity.config;

import com.example.demo.JWTSecurity.entrypoint.JwtAuthenticationEntryPoint;
import com.example.demo.JWTSecurity.filter.JwtAuthorizationTokenFilter;
import com.example.demo.JWTSecurity.userDetail.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    private JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                //开启跨域
                .cors().and()
                //自定义退出
                .logout().disable()
                //禁用匿名用户
                //.anonymous().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 全局不创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/sso/**").permitAll()
                .antMatchers("/user/**").permitAll()
                //获取验证码
                .antMatchers("/code/getCode").permitAll()
                // 登录接口放开过滤
                .antMatchers("/elasticSearch/*").permitAll()
                .antMatchers("/kafka/*").permitAll()
                .antMatchers("/mongo/*").permitAll()
                .antMatchers("/netty/*").permitAll()
                .antMatchers("/rabbitmq/*").permitAll()
                .antMatchers("/redis/**").permitAll()
                .antMatchers("/log/addLog").permitAll()
                .antMatchers("/user/login").permitAll()
                // session登录失效之后的跳转
                .antMatchers("/error/*").permitAll()
                //文件上传下载
                .antMatchers("/upload/**").permitAll()
                //定时任务
                .antMatchers("/schedule/**").permitAll()
                .antMatchers("/async/**").permitAll()
                //ehcache
                .antMatchers("/ehcache/**").permitAll()
                //QQ登录
                .antMatchers("/getQQCode").permitAll()
                .antMatchers("/QQLogin").permitAll()
                //solr
                .antMatchers("/solr/**").permitAll()
                .antMatchers("/message/**").permitAll()
                .antMatchers("/settle/**").permitAll()
                .antMatchers("/weChatSettle/**").permitAll()
                // 测试多数据源的接口，可以去掉
                //.antMatchers("/tran/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //禁用页面缓存
        httpSecurity
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }

    @Override
    public void configure(WebSecurity web) {
                web.ignoring()
                .antMatchers(HttpMethod.POST, "/login")
                // 静态资源放开过滤
                .and()
                .ignoring()
                .antMatchers(HttpMethod.GET, "/assets/**", "/favicon.ico", "/activiti-editor/**");
    }


}
