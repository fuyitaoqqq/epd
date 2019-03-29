package cn.ibeaver.service;

import cn.ibeaver.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * @author: fuyitao
 * @date: 2019/03/29
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        SysUser user = userService.getUserByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException(loginName);
        }
        return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(), emptyList());
    }
}
