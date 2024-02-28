package edu.ustc.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.ustc.shortlink.admin.dao.entity.GroupDO;
import edu.ustc.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import edu.ustc.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import edu.ustc.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

/**
 * @Author: ljx
 * @Date: 2024/2/26 11:05
 */
public interface GroupService extends IService<GroupDO> {
    void saveGroup(String groupName);

    void saveGroup(String username,String groupName);

    List<ShortLinkGroupRespDTO> listGroup();

    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    void deleteGroup(String gid);

    void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam);
}
