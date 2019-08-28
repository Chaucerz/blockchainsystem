package com.chaucer.blockchain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Chaucer
 * @date 2019-08-25 13:07
 */
@Controller
public class LoginController {


    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session) {
        if(!StringUtils.isEmpty(username) && password.equals("123456")){
            //登录成功
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        } else {
            //登录失败
            map.put("message","用户名或密码错误");
            return "index";
        }

    }
}
