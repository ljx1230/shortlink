package edu.ustc.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @Author: ljx
 * @Date: 2024/2/27 20:50
 */
@Data
public class ShortLinkRecycleBinPageReqDTO extends Page {
    private List<String> gidList;
}
