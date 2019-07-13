package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wu_zh
 * @Date 2019/7/13 14:50
 * @Classname LoginController
 * @Description TODO
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @RequestMapping("info")
    public Map<String, Object> name() {
        //从springsecurity中获取登录名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap<String, Object> map = new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}
