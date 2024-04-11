package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.vo.UserInfo;
import com.sicau.minordegreemanagement.facade.vo.UserRolePermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface UserService extends IService<User> {

    User getUserInfo(UserInfo userInfo);

    List<UserRolePermission> getUserRoleAndPermission(Integer userId);

    User getUserInfoByUserName(UserInfo userInfo);
}
