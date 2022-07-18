package com.lcx.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 realms 对象
 *      继承 AuthorizingRealm
 *          重写方法
 *              doGetAuthorizationInfo 授权方法
 *                  获取到用户的授权数据（用户权限数据）
 *              doGetAuthenticationInfo 认证
 *                  根据用户名密码登录，将用户数据保存
 */
public class PermissionRealm extends AuthorizingRealm {

    /*
        自定义 realm 名称
     */
    public void setName(String name){
        super.setName("permissionRealm");
    }

    /*
        principalCollection: 包含了所有已经授权的信息
        AuthorizationInfo: 授权数据
     */
    @Override
    // 授权：主要目的就是根据认真数据获取到用户权限信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /**
         * 1. 获取安全数据
         * 2. 根据 id 或者名称查询用户
         * 3. 查询用户的角色和权限信息
         * 4. 构造返回
         */
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<String> roles = new ArrayList<>();
        roles.add("role1");
        roles.add("role2");
        List<String> perms = new ArrayList<>();
        perms.add("user:save");
        perms.add("user:update");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);
        info.addRoles(roles);

        return info;
    }

    @Override
    // 认证的主要目的，比较用户和密码是否和数据库中的一致
    // 将安全数据存入到 shiro 进行保管
    // 参数：authenticationToken 登录构造的 usernamePasswordToken
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 1. 构造 uptoken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        // 2. 获取输入的用户名密码
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        // 3. 根据用户名查询数据库
        // 4. 比较密码和数据库中的密码是否一致（密码可能需要加密）
        if("123456".equals(password)) {
            // 5. 如果成功，向shiro存入安全数据
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName()); // 1. 安全数据 2. 密码 3. 当前 realm 域名称
            return info;
        }else {
            // 6. 失败就抛出异常或者返回 null
            throw new RuntimeException("用户或密码错误");
        }
    }
}
