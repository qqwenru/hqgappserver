package com.hqg.api.shiro;

import com.hqg.api.bean.Customer;
import com.hqg.api.bean.User;
import com.hqg.api.repository.CustomerRepository;
import com.hqg.api.service.UserService;
import com.hqg.api.util.JWTUtil;
import com.hqg.api.util.MD5Util;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class MyRealm extends AuthorizingRealm {

//    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

//    private UserService userService;
    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository){this.customerRepository = customerRepository;}

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String username = JWTUtil.getUsername(principals.toString());
//        User user = userService.getUser(username);
//        Customer customer = customerRepository.findByMobile(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addRole(user.getRole());
        String role = "user";
        simpleAuthorizationInfo.addRole(role);
        String perm = "view";
//        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
        Set<String> permission = new HashSet<>(Arrays.asList(perm.split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

//        User userBean = userService.getUser(username);
        Customer customer = customerRepository.findByMobile(username);
        if (customer == null) {
            throw new AuthenticationException("User didn't existed!");
        }

//        if (! JWTUtil.verify(token, username, userBean.getPassword())) {
        if (! JWTUtil.verify(token, username, customer.getPassword() )) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
