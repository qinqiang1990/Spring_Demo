package com.qq.service;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import shrio.Role;
import shrio.User;

public class MyShiro extends AuthorizingRealm {
	/** 
	 * 授权 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 获取登录时输入的用户名
		String loginName = (String) principals.fromRealm(getName()).iterator()
				.next();
		// 到数据库查是否有此对象
		User user = userService.findByName(loginName);
		if (user != null) {
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 用户的角色集合
			info.setRoles(user.getRolesName());
			// 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
			List<Role> roleList = user.getRoleList();
			for (Role role : roleList) {
				info.addStringPermissions(role.getPermissionsName());
			}
			return info;
		}
		return null;
	}

	/** 
	 *  验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 查出是否有此用户
		User user = userService.findByName(token.getUsername());
		if (user != null) {
			// 若存在，将此用户存放到登录认证info中
			return new SimpleAuthenticationInfo(user.getUsername(),
					user.getPassword(), getName());
		}
		return null;
	}

	/*
	 * public AuthenticationInfo getAuthenticationInfo(AuthenticationToken
	 * token) throws AuthenticationException { // TODO Auto-generated method
	 * stub String username = (String) token.getPrincipal(); // 得到用户名 String
	 * password = new String((char[]) token.getCredentials()); // 得到密码 if
	 * (!"qq".equals(username)) { throw new UnknownAccountException(); //
	 * 如果用户名错误 } if (!"123".equals(password)) { throw new
	 * IncorrectCredentialsException(); // 如果密码错误 } //
	 * 如果身份认证验证成功，返回一个AuthenticationInfo实现； return new
	 * SimpleAuthenticationInfo(username, password, getName()); }
	 * 
	 * public String getName() { // TODO Auto-generated method stub return
	 * "myRealm1"; }
	 * 
	 * public boolean supports(AuthenticationToken token) { // TODO
	 * Auto-generated method stub // 仅支持UsernamePasswordToken类型的Token return
	 * token instanceof UsernamePasswordToken; }
	 */
}
