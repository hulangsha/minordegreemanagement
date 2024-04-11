package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.mapper.UserMapper;
import com.sicau.minordegreemanagement.facade.service.UserService;
import com.sicau.minordegreemanagement.facade.vo.UserInfo;
import com.sicau.minordegreemanagement.facade.vo.UserRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserInfo(UserInfo userInfo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userInfo.getLoginName())
                .eq("password", userInfo.getPassword())
                .eq("isDelete", CommonCode.CONST_NUMBER_ONE.getCode());
        return this.getOne(queryWrapper);
    }

    @Override
    public List<UserRolePermission> getUserRoleAndPermission(Integer userId) {
        return userMapper.getUserRoleAndPermissionList(userId);
    }

    @Override
    public User getUserInfoByUserName(UserInfo userInfo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userInfo.getUserName());
        return this.getOne(queryWrapper);
    }
}
