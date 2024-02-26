package edu.ustc.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.ustc.shortlink.admin.dao.entity.GroupDO;

/**
 * @Author: ljx
 * @Date: 2024/2/26 11:05
 */
public interface GroupService extends IService<GroupDO> {
    void saveGroup(String groupName);
}
