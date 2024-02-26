package edu.ustc.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import edu.ustc.shortlink.admin.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: ljx
 * @Date: 2024/2/26 10:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_group")
public class GroupDO extends BaseDO {
    private Long id;
    private String gid;
    private String name;
    private String username;
    private Integer sortOrder;
}
