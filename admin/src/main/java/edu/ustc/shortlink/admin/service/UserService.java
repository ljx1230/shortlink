package edu.ustc.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.ustc.shortlink.admin.dao.entity.UserDO;
import edu.ustc.shortlink.admin.dto.req.UserLoginReqDTO;
import edu.ustc.shortlink.admin.dto.req.UserRegisterReqDTO;
import edu.ustc.shortlink.admin.dto.req.UserUpdateReqDTO;
import edu.ustc.shortlink.admin.dto.resp.UserLoginRespDTO;
import edu.ustc.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @Author: ljx
 * @Date: 2024/2/7 13:39
 */
public interface UserService extends IService<UserDO> {
    UserRespDTO getUserByUsername(String username);
    Boolean hasUsername(String username);
    void register(UserRegisterReqDTO userRegisterReqDTO);

    void update(UserUpdateReqDTO userUpdateReqDTO);

    UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO);

    Boolean checkLogin(String username,String token);

    void logout(String username, String token);
}
