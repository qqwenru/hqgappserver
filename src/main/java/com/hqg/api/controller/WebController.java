package com.hqg.api.controller;

import com.hqg.api.bean.ResponseBean;
import com.hqg.api.bean.User;
import com.hqg.api.exception.UnauthorizedException;
import com.hqg.api.repository.UserRepository;
import com.hqg.api.service.UserService;
import com.hqg.api.util.JWTUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebController {

//    private static final Logger LOGGER = LogManager.getLogger(WebController.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/enter")
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        List<User> list = userRepository.findByUsernameAndAndPassword(username, password);
//        User userBean = userService.getUser(username);
//        if (userBean.getPassword().equals(password)) {
        if (list.size() == 1){
            String token = JWTUtil.sign(username, password);
            Map<String,String> map = new HashMap<>();
            map.put("token",token);
//            return new ResponseBean(200, "Login success", JWTUtil.sign(username, password));
            return new ResponseBean(200, "Login success", map);
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }
}
