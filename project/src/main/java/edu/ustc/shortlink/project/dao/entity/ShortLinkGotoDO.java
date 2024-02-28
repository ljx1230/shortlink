package edu.ustc.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import edu.ustc.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ljx
 * @Date: 2024/2/28 20:39
 */
@Data
@TableName("t_link_goto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkGotoDO{
    private Long id;
    private String gid;
    private String fullShortUrl;
}
