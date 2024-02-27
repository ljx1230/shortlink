package edu.ustc.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ustc.shortlink.project.dao.entity.ShortLinkDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ljx
 * @Date: 2024/2/27 14:33
 */
@Mapper
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {
}
