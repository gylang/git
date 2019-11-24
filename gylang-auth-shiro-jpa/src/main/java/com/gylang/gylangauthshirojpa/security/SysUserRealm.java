package com.gylang.gylangauthshirojpa.security;

import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
import com.gylang.gylangauthshirojpa.contant.SysConstants;
import com.gylang.gylangauthshirojpa.domian.SysMenu;
import com.gylang.gylangauthshirojpa.domian.SysUser;
import com.gylang.gylangauthshirojpa.enums.LoginEnum;
import com.gylang.gylangauthshirojpa.exception.domian.LoginException;
import com.gylang.gylangauthshirojpa.service.SysMenuService;
import com.gylang.gylangauthshirojpa.service.SysUserService;
import com.gylang.gylangauthshirojpa.utils.CopyUtils;
import com.gylang.gylangauthshirojpa.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SysUserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    /* 用户认证 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进行授权...");
        LoginInfoDTO loginInfoDTO = (LoginInfoDTO) principalCollection.getPrimaryPrincipal();
        System.out.println(JsonUtils.obj2Json(loginInfoDTO));
        List<SysMenu> sysMenuList;
        if (SysConstants.ADMIN.equals(loginInfoDTO.getName())) {
            System.out.println("超管");
            sysMenuList = sysMenuService.findAll();
        } else {
            System.out.println("普通用户");
            sysMenuList = sysMenuService.findByUserId(loginInfoDTO.getId());
        }
        System.out.println(sysMenuList);

        List<String> menu = sysMenuList.stream()
                .filter(item -> !StringUtils.isEmpty(item.getPerms()))
                .filter(item -> !"authc".equals(item.getPerms()))
                .filter(item -> !"anon".equals(item.getPerms()))

                .map(SysMenu::getPerms).collect(Collectors.toList());
        System.out.println(menu);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(menu);
        return simpleAuthorizationInfo;
    }

    /* 用户授权 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        System.out.println("进行验证...");
        String userName = (String) authenticationToken.getPrincipal();

        SysUser sysUser = sysUserService.findByUserName(userName);
        if (null == sysUser) {
            throw new LoginException(LoginEnum.USER_NOT_FOUND);
        }
        if (0 == sysUser.getStatus()) {
            throw new LoginException(LoginEnum.USER_STOP);
        }
        LoginInfoDTO loginInfo = new LoginInfoDTO();
        CopyUtils.notNullCopy(sysUser, loginInfo);
//       log.info("[ DefaultSubjectContext.PRINCIPALS_SESSION_KEY] : {}" ,DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(loginInfo, sysUser.getPassword(), this.getClass().getName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getSalt()));

        return simpleAuthenticationInfo;

    }
}
