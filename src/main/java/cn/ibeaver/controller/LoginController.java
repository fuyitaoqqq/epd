package cn.ibeaver.controller;

import cn.ibeaver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private SysUserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /*@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultDto register(SysUser user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setSalt("salt");
        userService.saveUser(user);
        return ResultDto.success();

    }*/

}
