package com.youo.bookmanage.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youo.bookmanage.Utils.JWTUtil;
import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.response.ResultCode;
import com.youo.bookmanage.security.Role;
import com.youo.bookmanage.system.entity.User;
import com.youo.bookmanage.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pt
 * @since 2020-11-11
 */
@CrossOrigin
@Slf4j
//相当于@Controller+@ResponseBody
@RestController
@RequestMapping("/system/user")
@Api(value="用户模块",tags="用户接口")
public class UserController {
    @Autowired
    private UserService userService;



/*    @ApiOperation(value="用户登出",notes="")
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request,HttpServletResponse response){

        Boolean f = false;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if((cookie.getName()).equals("token")){
                f = true;
            }
        }
        if(f == false){
            throw new BusinessException(USER_NO_RECORD.getCode(), USER_NO_RECORD.getMessage());
        }
        Cookie cookie = new Cookie("token", null);
        *//*解决浏览器保存不了cookie*//*
        cookie.setPath("/");
        response.addCookie(cookie);
        return Result.ok();

    }*/

    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     */
    @ApiOperation(value="用户登录",notes="接收参数用户名和密码")
    @GetMapping("/login")
    public Result login(@RequestParam("name")@NotBlank(message = "账号必填") String name,
                        @RequestParam("password")@NotBlank(message = "密码必填") String password,
                        HttpServletResponse response) {
        User user = userService.findUserByName(name);
        if (user != null) {
          //  使用双等号不行
            if(user.getPassword().equals(password)){
                Map<String, String> map = new HashMap<>(); //用来存放payload信息

                map.put("id",user.getUid().toString());
                map.put("username",user.getName());
                map.put("role", String.valueOf(user.getPortid()));


                // 生成token令牌
                String token = JWTUtil.generateToken(map);
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);


                return Result.ok();

            }else {
                return Result.error().code(ResultCode.USER_CREDENTIALS_ERROR.
                        getCode()).message(ResultCode.USER_CREDENTIALS_ERROR.getMessage());
            }
        }else {
            return Result.error().code(ResultCode.USER_ACCOUNT_NOT_EXIST.
                    getCode()).message(ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
    }


    /**
     * 分页查询用户列表
     * @return
     */
    /*Ambiguous handler methods,需要更改请求url*/
    @Role(roles= "1")
    @GetMapping("/all/{current}/{size}")
    @ApiOperation(value="分页用户列表",notes="查询所有用户信息")
    public Result findUsersList(@PathVariable Integer current,
                                @PathVariable Integer size){


        Page<User> page = new Page<>(current,size);
        Page<User> usersPage = userService.page(page);
        long total = usersPage.getTotal();
        List<User> records = usersPage.getRecords();
        return Result.ok().data("total",total).data("records",records);


    }

    /**
     * 添加用户
     * @param user
     * @return
     */
//添加用户接口需要接收对象，故不用restful风格命名
    @Role(roles="1")
    @ApiOperation(value = "添加用户",notes = "添加用户信息")
    @PostMapping("")
    public Result addUser(@Validated @RequestBody/* 前端传递json数据到后端 */  User user){
        userService.addUser(user);
        return Result.ok();
    }

    /**
     * 删除用户
     * @param name
     * @return
     */
    @Role(roles="1")
    @ApiOperation(value = "删除用户",notes = "删除用户通过名字" )
    @DeleteMapping("/{name}")
//    接收请求路径中占位符的值
    public Result delete(@PathVariable String name){

        User user = userService.findUserByName(name);
        int id = user.getUid();
        userService.deleteById(id);
        return Result.ok();
    }

    /**
     * 修改用户密码
     * @param name
     * @param psw
     * @param newpsw
     * @return
     */
    @Role(roles="1")
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @PutMapping("/{name}/{psw}/{newpsw}")
    public Result updateUserPsw(@PathVariable String name,@PathVariable String psw,@PathVariable String newpsw) {
        userService.updateUserPsw(name,psw,newpsw);
        return Result.ok();

    }



    @Role(roles="1")
    @GetMapping("/all/{current}/{size}/{partid}")
    @ApiOperation(value="分页角色用户列表",notes="分页查询某角色用户信息")
    public Result findPortUList(@PathVariable Integer current,
                                @PathVariable Integer size,
                                @PathVariable Integer partid){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("portid",partid);
        Page<User> page = new Page<>(current,size);
        Page<User> usersPage = userService.page(page,wrapper);
        long total = usersPage.getTotal();
        List<User> records = usersPage.getRecords();
        return Result.ok().data("total",total).data("records",records);


    }

}

