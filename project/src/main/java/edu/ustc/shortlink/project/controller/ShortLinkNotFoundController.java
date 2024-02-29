package edu.ustc.shortlink.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: ljx
 * @Date: 2024/2/29 14:38
 */
@Controller
public class ShortLinkNotFoundController {
    @RequestMapping("/page/notfound")
    public String notfound() {
        return "notfound";
    }
}
