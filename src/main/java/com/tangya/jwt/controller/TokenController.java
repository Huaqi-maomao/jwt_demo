package com.tangya.jwt.controller;


import com.tangya.jwt.config.JwtConfig;
import com.tangya.jwt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("token")
public class TokenController {

    @Autowired
    private JwtConfig jwtConfig;


    /**
     * 模拟用户登录
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam(name = "userName") String userName,
                        @RequestParam(name = "password") String password){
        /** 验证userName，passWord和数据库中是否一致，如不一致，直接return Result.errer(); 【这里省略该步骤】*/

        // 这里模拟通过用户名和密码，从数据库查询userId
        // 这里把userId转为String类型，实际开发中如果subject需要存userId，则可以JwtConfig的createToken方法的参数设置为Long类型
        String token = jwtConfig.createToken(userName);
        return Result.success(token);
    }

    /**
     * 需要token 验证的 端口
     * @return
     */
    @GetMapping("/info")
    public Result getInfo(){
        return Result.success("info");
    }


    /**
     * 根据请求头获取用户
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request){
        String userNameFromToke = jwtConfig.getUserNameFromToke(request.getHeader("token"));
        return Result.success(userNameFromToke);
    }
}
