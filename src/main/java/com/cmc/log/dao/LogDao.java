package com.cmc.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmc.log.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午10:40
 */
@Mapper
public interface LogDao extends BaseMapper<LogEntity> {
}
