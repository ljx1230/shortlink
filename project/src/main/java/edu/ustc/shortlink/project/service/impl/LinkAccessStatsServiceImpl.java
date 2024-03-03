package edu.ustc.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import edu.ustc.shortlink.project.dao.entity.LinkAccessStatsDO;
import edu.ustc.shortlink.project.dao.mapper.LinkAccessStatsMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: ljx
 * @Date: 2024/3/2 12:17
 */
@Service
public class LinkAccessStatsServiceImpl extends ServiceImpl<LinkAccessStatsMapper, LinkAccessStatsDO> implements IService<LinkAccessStatsDO> {
}
