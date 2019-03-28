package cn.ibeaver.config;

import cn.ibeaver.realm.EpdRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager());
        //filter.setLoginUrl();
        //filter.setUnauthorizedUrl();

        Map<String, String> filterMap = new LinkedHashMap<>();
        //filterMap.put("/**", "authc");
        filterMap.put("/**", "anon");

        filter.setFilterChainDefinitionMap(filterMap);

        return filter;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(epdRealm());
        return securityManager;
    }

    @Bean
    public EpdRealm epdRealm() {
        return new EpdRealm();
    }

}
