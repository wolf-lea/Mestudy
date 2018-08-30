package com.tecsun.sisp.adapter.modules.account.entity.response;

/**账号角色
 * Created by danmeng on 2017/5/5.
 */
public class RoleVO {
    private long roleId;//角色ID
    private String roleCode;//角色编码
    private String roleName;//角色名称
    private String roleStatus;//角色状态:0=不可用,1=可用
    private String description;//描述
    private String parentRoleCode;//上级角色编码

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentRoleCode() {
        return parentRoleCode;
    }

    public void setParentRoleCode(String parentRoleCode) {
        this.parentRoleCode = parentRoleCode;
    }
}
