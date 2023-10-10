package com.djy.controller.user;

import com.djy.common.Result;
import com.djy.pojo.User;
import com.djy.service.RoleService;
import com.djy.service.UserService;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation("修改密码，需要id，userPassword（新的密码）")
    @PostMapping("/updatePwd")
    private Result<?> updatePwd(@RequestBody User user) {

        Integer id = user.getId();
        String newUserPassword = user.getUserPassword();
        boolean flag = false;
        if (!StringUtils.isNullOrEmpty(newUserPassword)) {

            flag = userService.updatePwd(id, newUserPassword);
            if (flag) {
                Result res = Result.success();
                res.setMsg("修改密码成功，请退出，使用新密码登录");
                return res;
            } else {
                return Result.error("1", "修改失败");
            }
        } else {
            return Result.error("1", "新密码有问题");
        }
    }

    @ApiOperation("分页获取user列表，可选username，userRole，pageIndex")
    @PostMapping("/query/{pageIndex}/{pageSize}")
    private Result<?> query(@RequestBody User user, @PathVariable int pageIndex,@PathVariable int pageSize) {

        String userName = user.getUserName();
        Integer userRole = user.getUserRole();

        if (userName==null||userName.equals("string")) {
            userName = "";
        }
        if(userRole==null){
            userRole = 0;
        }
        //获取用户列表展示
        HashMap<String, Object> map = userService.getUserList(userName, userRole, pageIndex, pageSize);

        return Result.success(map);
    }

    @ApiOperation("用于在修改密码时，判断旧密码是否正确，需要id")
    @PostMapping("/getPwdById/{id}")
    private Result<?> getPwdById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return Result.success(user.getUserPassword());
    }


    @ApiOperation("通过Id修改user信息，需要user")
    @PostMapping("modifyById")
    private Result<?> modifyById(@RequestBody User user) {
        System.out.println(user);
        boolean flag = userService.modifyById(user);
        if (flag) {
            return Result.success();
        } else {
            return Result.error("1", "修改失败");
        }
    }

    @ApiOperation("通过id获取user，需要id")
    @PostMapping("/getUserById/{id}")
    private Result<?> getUserById(@PathVariable int id) {
        if (id > 0) {
            User user = userService.getUserById(id);
            return Result.success(user);
        } else {
            return Result.error("1", "id不合法");
        }

    }

    @ApiOperation("通过id删除user，需要id")
    @PostMapping("/delUserById/{id}")
    private Result<?> delUserById(@PathVariable int id) {
        if (id > 0) {
            boolean flag = userService.deleteUserById(id);
            return Result.success(flag);
        } else {
            return Result.error("1", "id不合法");
        }
    }


    @ApiOperation("判断userCode是否存在,需要userCode")
    @PostMapping("/userCodeExist/{userCode}")
    private Result<?> userCodeExist(@PathVariable String userCode) {

        HashMap<String, String> resultMap = new HashMap<String, String>();

        if (userCode.equals("string") || userCode.equals("")) {
            return Result.success();
        } else {
            User user = userService.selectUserCodeExist(userCode);
            if (user != null) {
                return Result.success();
            } else {
                return Result.error("1", "不存在");
            }
        }
    }

    @ApiOperation("获取role列表，不需要参数")
    @PostMapping("/getRoleList")
    private Result<?> getRoleList(){
        return Result.success(roleService.getRoleList());
    }

    @ApiOperation("添加user，需要user，不用Id")
    @PostMapping("/add")
    private Result<?> add(@RequestBody User user){
        user.setId(null);
        boolean flag = userService.add(user);
        if(flag){
            return Result.success();
        }else{
            return Result.error("1","添加失败");
        }
    }

}
