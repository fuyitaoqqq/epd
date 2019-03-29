package cn.ibeaver.dao;

import cn.ibeaver.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

/**
 * @author: fuyitao
 * @date: 2019/03/29
 */
@Component
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 保存
     * @param user
     * @return
     */
    @Insert("saveUser")
    int saveUser(SysUser user);

}
