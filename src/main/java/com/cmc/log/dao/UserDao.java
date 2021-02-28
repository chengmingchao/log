package com.cmc.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmc.log.entity.UserCar;
import com.cmc.log.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:22
 */
public interface UserDao extends BaseMapper<UserEntity> {

    UserCar queryByUserId(@Param("id") Long id);
}
