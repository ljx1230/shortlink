package edu.ustc.shortlink.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ljx
 * @Date: 2024/2/6 23:42
 */
@RestController
public class UserController {
    @GetMapping("/api/short-link/v1/user/{username}")
    public String getUserByUsername(@PathVariable("username") String username) {
        return "Hi " + username;
    }
}
