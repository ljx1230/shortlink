package edu.ustc.shortlink.project.controller;

import edu.ustc.shortlink.project.common.convention.result.Result;
import edu.ustc.shortlink.project.common.convention.result.Results;
import edu.ustc.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import edu.ustc.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import edu.ustc.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ljx
 * @Date: 2024/2/27 14:43
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/short-link/v1")
public class ShortLinkController {
    private final ShortLinkService shortLinkService;
    @PostMapping("/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        ShortLinkCreateRespDTO shortLinkCreateRespDTO = shortLinkService.createShortLink(requestParam);
        return Results.success(shortLinkCreateRespDTO);
    }
}
