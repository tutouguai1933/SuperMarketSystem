package com.djy.controller.user;

import com.djy.common.Result;
import com.djy.pojo.User;
import com.djy.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation("判断登录，需要userCode，userPassword")
    @PostMapping()
    protected Result<?> login(@RequestBody User user) throws ServletException, IOException {

        String userCode = user.getUserCode();
        String userPassword = user.getUserPassword();
        user =  userService.login(userCode,userPassword);
        if (user!=null){
            if (user.getUserPassword().equals(userPassword)){
                return Result.success(user);
            }else {
                return Result.error("1","密码错误");
            }
        }else{
            return Result.error("1","用户名错误");
        }
    }
}
