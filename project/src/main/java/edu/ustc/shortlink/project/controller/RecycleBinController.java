package edu.ustc.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.ustc.shortlink.project.common.convention.result.Result;
import edu.ustc.shortlink.project.common.convention.result.Results;
import edu.ustc.shortlink.project.dto.req.*;
import edu.ustc.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import edu.ustc.shortlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ljx
 * @Date: 2024/3/1 14:46
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/short-link/v1/recycle-bin")
public class RecycleBinController {
    private final RecycleBinService recycleBinService;
    @PostMapping("/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }
    @GetMapping("/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return Results.success(recycleBinService.pageShortLink(requestParam));
    }
    @PostMapping("/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam) {
        recycleBinService.recover(requestParam);
        return Results.success();
    }
    @PostMapping("/remove")
    public Result<Void> removerRecycleBin(@RequestBody RecycleBinRemoverReqDTO requestParam) {
        recycleBinService.removeRecycleBin(requestParam);
        return Results.success();
    }
}
