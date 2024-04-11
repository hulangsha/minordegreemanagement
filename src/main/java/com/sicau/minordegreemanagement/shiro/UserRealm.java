package com.sicau.minordegreemanagement.shiro;

import com.alibaba.druid.util.StringUtils;
import com.sicau.minordegreemanagement.common.component.JwtUtil;
import com.sicau.minordegreemanagement.common.component.SpringContextHolder;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.service.UserService;
import com.sicau.minordegreemanagement.facade.vo.UserInfo;
import com.sicau.minordegreemanagement.facade.vo.UserRolePermission;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {

    /**
     * 支持Token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始授权~~~~~~~~~~~~~~");
        //获取用户名
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (Objects.isNull(user)) {
            throw new AuthorizationException("该用户没有权限");
        }
        Integer userId = user.getUserId();
        if (0 == userId || null == userId) {
            throw new AuthorizationException("该用户没有权限");
        }
        UserService userService = SpringContextHolder.getBean(UserService.class);
        List<UserRolePermission> userRolePermissionList = userService.getUserRoleAndPermission(userId);
        if (CollectionUtils.isEmpty(userRolePermissionList)) {
            throw new AuthorizationException();
        }
        HashSet<String> roleSet = new HashSet<>();
        HashSet<String> permissionSet = new HashSet<>();
        for (UserRolePermission userRolePermission : userRolePermissionList) {
            roleSet.add(userRolePermission.getRoleName());
            permissionSet.add(userRolePermission.getPermissionUrl());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleSet);
        info.addStringPermissions(permissionSet);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("开始身份认证~~~~~~~~~~~");
        //获取token
        String token = authenticationToken.getPrincipal().toString();
        //获取用户名
        String userName = null;
        log.info("token的值UserRealm: {}",token);
        try {
            JwtUtil jwtUtil = SpringContextHolder.getBean(JwtUtil.class);
            userName = jwtUtil.getUserName(token);
        } catch (Exception e) {
            System.out.println(e);
            throw new AuthenticationException(e + "token拼写错误，或者值为空");
        }
        if (StringUtils.isEmpty(userName)) {
            throw new AuthenticationException("token无效");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        User user = null;
        try {
            UserService userService = SpringContextHolder.getBean(UserService.class);
            user = userService.getUserInfoByUserName(userInfo);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        if (Objects.isNull(user)){
            throw new AuthenticationException("用户不存在");
        }
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token, this.getName());
            return info;
    }

}
