package cn.ibeaver.dao;

import cn.ibeaver.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {

    User getUserByUsername(String username);

}
