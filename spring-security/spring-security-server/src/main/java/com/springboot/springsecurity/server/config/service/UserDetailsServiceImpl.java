package com.springboot.springsecurity.server.config.service;

import com.google.common.collect.Lists;
import com.springboot.springsecurity.server.domain.TbPermission;
import com.springboot.springsecurity.server.domain.TbUser;
import com.springboot.springsecurity.server.service.TbPermissionService;
import com.springboot.springsecurity.server.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/4
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private TbUserService tbUserService;

    @Resource
    private TbPermissionService tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TbUser tbUser = tbUserService.selectByUserName(username);
        List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(tbUser.getId());

        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();

        tbPermissions.forEach(permission -> {
            if(null != permission && StringUtils.isNotBlank(permission.getEnname())) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(permission.getEnname()));
            }
        });
        return new User(username, tbUser.getPassword(), grantedAuthorityList);
    }

}
