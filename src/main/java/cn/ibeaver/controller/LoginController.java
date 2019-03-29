package cn.ibeaver.controller;

import cn.ibeaver.dto.ResultDto;
import cn.ibeaver.pojo.SysUser;
import cn.ibeaver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private SysUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultDto login(String loginName, String password) {



        return ResultDto.success();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultDto register(SysUser user) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setSalt("salt");
        userService.saveUser(user);
        return ResultDto.success();

    }

}
