package edu.ustc.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.ustc.shortlink.admin.common.convention.result.Result;
import edu.ustc.shortlink.admin.common.convention.result.Results;
import edu.ustc.shortlink.admin.remote.ShortLinkRemoteService;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import edu.ustc.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import edu.ustc.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ljx
 * @Date: 2024/2/27 22:20
 */
@RestController
@RequestMapping("/api/short-link/admin/v1")
public class ShortLinkController {
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {};
    @GetMapping("/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }

    @PostMapping("/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    @PostMapping("/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkRemoteService.updateShortLink(requestParam);
        return Results.success();
    }
}
