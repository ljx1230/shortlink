package edu.ustc.shortlink.admin.controller;

import edu.ustc.shortlink.admin.common.convention.result.Result;
import edu.ustc.shortlink.admin.common.convention.result.Results;
import edu.ustc.shortlink.admin.common.enums.UserErrorCodeEnum;
import edu.ustc.shortlink.admin.dto.req.UserRegisterReqDTO;
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
public class UserController {
    private final UserService userService;
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO respDTO = userService.getUserByUsername(username);
        return Results.success(respDTO);
    }

    @GetMapping("/api/short-link/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    @PostMapping("/api/short-link/v1/user/register")
    public Result register(@RequestBody UserRegisterReqDTO userRegisterReqDTO) {
        userService.register(userRegisterReqDTO);
        return Results.success();
    }

}
