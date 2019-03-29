package cn.ibeaver.config;

import cn.ibeaver.filter.JwtAuthenticationFilter;
import cn.ibeaver.filter.JwtLoginFilter;
import cn.ibeaver.service.SysUserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: fuyitao
 * @date: 2019/03/29
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http./*cors().and().*/csrf().disable()
                //所有请求进行验证
                .authorizeRequests()
                //对指定url放行
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                //所有请求需要身份认证
                .anyRequest().authenticated()
                // 权限检查
                //.antMatchers("/hello").hasAuthority("AUTH_WRITE")
                // 角色检查
                //.antMatchers("/world").hasRole("ADMIN")
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
