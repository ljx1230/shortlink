package edu.ustc.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.ustc.shortlink.admin.dao.entity.UserDO;
import edu.ustc.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @Author: ljx
 * @Date: 2024/2/7 13:39
 */
public interface UserService extends IService<UserDO> {
    UserRespDTO getUserByUsername(String username);
}
