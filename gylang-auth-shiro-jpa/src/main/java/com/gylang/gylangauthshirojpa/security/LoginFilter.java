//package com.gylang.gylangauthshirojpa.security;
//
//import com.gylang.gylangauthshirojpa.DTO.LoginInfoDTO;
//import lombok.AllArgsConstructor;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.AccessControlFilter;
//import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
//import org.apache.shiro.web.filter.authc.AuthenticationFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
///**
// * @author gylang,
// * @data 2019/11/16 16:52,
// * @DESC
// */
//@AllArgsConstructor
//public  class LoginFilter extends AuthenticationFilter {
//
//    private OnlineUserService onlineUserService;
//
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        System.out.println("-------------------------------触发拦截器--------------------------------");
//        Subject subject = this.getSubject(request, response);
//        if (subject.isAuthenticated()) {
//            LoginInfoDTO loginInfoDTO = (LoginInfoDTO) subject.getPrincipal();
//            onlineUserService.updateOnlineExpire(loginInfoDTO.getId());
//            return true;
//        } else {
//            return false;
//        }
//
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        return false;
//    }
//
//
//}
