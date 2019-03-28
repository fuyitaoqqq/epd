package cn.ibeaver.controller;

import cn.ibeaver.dto.ResultDto;
import cn.ibeaver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResultDto login(String username) {



        return null;
    }

}
