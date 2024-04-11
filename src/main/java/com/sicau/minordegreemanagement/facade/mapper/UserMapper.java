package com.sicau.minordegreemanagement.facade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.vo.UserRolePermission;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserRolePermission> getUserRoleAndPermissionList(Integer userId);
}
