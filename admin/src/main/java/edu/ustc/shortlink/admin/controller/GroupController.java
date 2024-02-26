package edu.ustc.shortlink.admin.controller;

import edu.ustc.shortlink.admin.common.convention.result.Result;
import edu.ustc.shortlink.admin.common.convention.result.Results;
import edu.ustc.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import edu.ustc.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ljx
 * @Date: 2024/2/26 11:07
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/short-link/v1/group")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }
}
