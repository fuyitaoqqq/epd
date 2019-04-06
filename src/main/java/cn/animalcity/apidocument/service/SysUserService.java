package cn.animalcity.apidocument.service;

import cn.animalcity.apidocument.dao.SysUserMapper;
import cn.animalcity.apidocument.pojo.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fuyitao
 * @date: 2019/04/06
 */

@Service("userService")
public class SysUserService {

    private final SysUserMapper userMapper;

    @Autowired
    public SysUserService(SysUserMapper sysUserMapper) {
        this.userMapper = sysUserMapper;
    }

    public SysUser getUserByLoginName(String loginName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);

        return userMapper.selectOne(queryWrapper);
    }

}
