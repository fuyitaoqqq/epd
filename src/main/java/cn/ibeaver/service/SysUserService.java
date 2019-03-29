package cn.ibeaver.service;

import cn.ibeaver.dao.SysUserMapper;
import cn.ibeaver.pojo.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fuyitao
 * @date: 2019/03/29
 */

@Service("userService")
@Slf4j
public class SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    public SysUser getUserByLoginName(String loginName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);

        SysUser sysUser = userMapper.selectOne(queryWrapper);

        return sysUser;
    }

    public int saveUser(SysUser user){
        return userMapper.saveUser(user);
    }

}
