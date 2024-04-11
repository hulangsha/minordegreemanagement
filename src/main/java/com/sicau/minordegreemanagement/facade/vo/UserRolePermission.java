package com.sicau.minordegreemanagement.facade.vo;

import lombok.Data;

@Data
public class UserRolePermission {
    private Integer userId;
    private Integer roleId;
    private String roleCode;
    private Integer permissionId;
    private String userName;
    private String roleName;
    private String permissionUrl;
    private String permissionName;
}
