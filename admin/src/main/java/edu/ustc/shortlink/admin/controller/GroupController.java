package edu.ustc.shortlink.admin.controller;

import edu.ustc.shortlink.admin.common.convention.result.Result;
import edu.ustc.shortlink.admin.common.convention.result.Results;
import edu.ustc.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import edu.ustc.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import edu.ustc.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import edu.ustc.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import edu.ustc.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: ljx
 * @Date: 2024/2/26 11:07
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/short-link/admin/v1/group")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    @PutMapping
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    @DeleteMapping
    public Result<Void> deleteGroup(@RequestParam("gid") String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    @PostMapping("/sort")
    public Result<Void> sort(@RequestBody List<ShortLinkGroupSortReqDTO> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }
}
