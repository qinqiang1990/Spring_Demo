/*package shrio.tt100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.cache.annotation.Cacheable;

import com.hfstu.framework.md5.MD5;
import com.hfstu.weixin.model.Custom;
import com.hfstu.weixin.model.RoleUser;
import com.hfstu.weixin.service.ICustomService;
import com.hfstu.weixin.service.IRoleService;
import com.hfstu.weixin.service.IRoleUserService;

*//**
 * 权限认证
 * 
 * @version 3.0
 *//*
public class AuthenticationRealm extends AuthorizingRealm {
	
	@Resource
	private IService customService;
	@Resource
	private IRoleUserService roleUserService;
	@Resource
	private IRoleService roleService;
	*//**
	 * 获取认证信息
	 * 
	 * @param token
	 *            令牌
	 * @return 认证信息
	 *//*
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		Custom custom = customService.getByUsername(usernamePasswordToken.getUsername());
		if(custom == null){
			throw new UnknownAccountException();
		}
		String password = custom.getPassword();
		if(password.equals(new String(usernamePasswordToken.getPassword()))){
			return new SimpleAuthenticationInfo(new Principal(custom.getId(), custom.getUsername()), custom.getPassword(), getName());
		}else{
			throw new IncorrectCredentialsException();
		}
	}

	*//**
	 * 获取授权信息
	 * 
	 * @param principals
	 *            principals
	 * @return 授权信息
	 *//*
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = new ArrayList<String>();
			authorities.add("wechat:wechat");
			authorities.add("wechat:wxsite");
			authorities.add("wechat:wxsitekjdh");
			
			String filterString0 = "from RoleUser roleUser where userId='"+principal.getId()+"'";
			List<RoleUser> roleUsers = roleUserService.findListByHQL(filterString0);
			String sysMenuIds = "";
			for(int i=0;i<roleUsers.size();i++){
				sysMenuIds = sysMenuIds+","+roleService.getById(roleUsers.get(i).getRoleId()).getMenuStr();
			}
			if(StringUtils.isNotBlank(sysMenuIds)){
				sysMenuIds = StringUtils.removeStart(sysMenuIds, ",");
				authorities.addAll(Arrays.asList(sysMenuIds.split(",")));
			}
			
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}*/