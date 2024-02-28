package edu.ustc.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Author: ljx
 * @Date: 2024/2/28 18:14
 */
@RequiredArgsConstructor
public enum ValidDateTypeEnum {
    PERMANENT(0),
    CUSTOM(1);

    @Getter
    private final int type;
}
