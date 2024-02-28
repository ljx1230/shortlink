package edu.ustc.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.ustc.shortlink.project.common.convention.result.Result;
import edu.ustc.shortlink.project.common.convention.result.Results;
import edu.ustc.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import edu.ustc.shortlink.project.dto.req.ShortLinkPageReqDTO;
import edu.ustc.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import edu.ustc.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import edu.ustc.shortlink.project.dto.resp.ShortLinkGroupCntQueryRespDTO;
import edu.ustc.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import edu.ustc.shortlink.project.service.ShortLinkService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author: ljx
 * @Date: 2024/2/27 14:43
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        ShortLinkCreateRespDTO shortLinkCreateRespDTO = shortLinkService.createShortLink(requestParam);
        return Results.success(shortLinkCreateRespDTO);
    }

    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCntQueryRespDTO>> listGroupShortLinkCount(@RequestParam List<String> groupIds) {
        return Results.success(shortLinkService.listGroupShortLinkCount(groupIds));
    }
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }
    @GetMapping("/{short-uri}")
    public void restoreUrl(@PathVariable("short-uri")String shortUri, HttpServletRequest request, HttpServletResponse response){
        shortLinkService.restoreUrl(shortUri,request,response);
    }
}
