package edu.ustc.shortlink.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ustc.shortlink.admin.dao.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ljx
 * @Date: 2024/2/7 13:39
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
