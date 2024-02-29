package edu.ustc.shortlink.project.controller;

import edu.ustc.shortlink.project.common.convention.result.Result;
import edu.ustc.shortlink.project.common.convention.result.Results;
import edu.ustc.shortlink.project.service.UrlTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ljx
 * @Date: 2024/2/29 14:46
 */
@RestController
@RequiredArgsConstructor
public class UrlTitleController {

    private final UrlTitleService urlTitleService;

    /**
     * 根据URL获取对应网站标题
     * @param url
     * @return
     */
    @GetMapping("/api/short-link/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        String title = urlTitleService.getTitleByUrl(url);
        return Results.success(title);
    }
}
