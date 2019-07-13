package com.pinyougou.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wu_zh
 * @Date 2019/7/13 19:12
 * @Classname LoginController
 * @Description 登录显示商家名称
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @RequestMapping("info")
    public Map<String, Object> name() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap<String, Object> map = new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}
