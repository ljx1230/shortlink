package edu.ustc.shortlink.admin.controller;

import edu.ustc.shortlink.admin.common.convention.result.Result;
import edu.ustc.shortlink.admin.common.convention.result.Results;
import edu.ustc.shortlink.admin.dto.req.UserLoginReqDTO;
import edu.ustc.shortlink.admin.dto.req.UserRegisterReqDTO;
import edu.ustc.shortlink.admin.dto.req.UserUpdateReqDTO;
import edu.ustc.shortlink.admin.dto.resp.UserLoginRespDTO;
import edu.ustc.shortlink.admin.dto.resp.UserRespDTO;
import edu.ustc.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ljx
 * @Date: 2024/2/6 23:42
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/short-link/admin/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO respDTO = userService.getUserByUsername(username);
        return Results.success(respDTO);
    }

    @GetMapping("/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    @PostMapping()
    public Result<Void> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO) {
        userService.register(userRegisterReqDTO);
        return Results.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody UserUpdateReqDTO userUpdateReqDTO) {
        userService.update(userUpdateReqDTO);
        return Results.success();
    }

    @PostMapping("/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginReqDTO) {
        UserLoginRespDTO result = userService.login(userLoginReqDTO);
        return Results.success(result);
    }

    @GetMapping("/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username,@RequestParam("token") String token) {
        Boolean res = userService.checkLogin(username,token);
        return Results.success(res);
    }

    @DeleteMapping("/logout")
    public Result<Void> logout(@RequestParam("username") String username,@RequestParam("token") String token) {
        userService.logout(username,token);
        return Results.success();
    }
}
