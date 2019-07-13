package com.pinyougou.shop.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wu_zh
 * @Date 2019/7/13 17:48
 * @Classname UserDetailsServiceImpl
 * @Description TODO
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Reference
    private SellerService sellerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug(username + ",进入了loadUserByUsername方法...");
        //构造用户的角色列表
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        TbSeller seller = sellerService.getById(username);
        if (seller != null && "1".equals(seller.getStatus())) {
            //返回真实存在的用户,让security框架对配置用户与密码信息是否匹配
            return new User(username, seller.getPassword(), authorities);
        } else {
            return null;
        }
    }
}
