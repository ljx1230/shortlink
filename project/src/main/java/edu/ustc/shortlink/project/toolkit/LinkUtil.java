package edu.ustc.shortlink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Optional;

import static edu.ustc.shortlink.project.common.constant.ShortLinkConstant.DEFAULT_CACHE_VALID_TIME;

/**
 * @Author: ljx
 * @Date: 2024/2/29 14:07
 */
public class LinkUtil {
    public static long getLinkCacheValidTime(Date validDate) {
        return Optional.ofNullable(validDate)
                .map(item -> DateUtil.between(new Date(),item, DateUnit.MS,false))
                .orElse(DEFAULT_CACHE_VALID_TIME);
    }
}
