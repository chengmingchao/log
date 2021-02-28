package com.cmc.log.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.log.annotation.ServiceLog;
import com.cmc.log.dao.UserDao;
import com.cmc.log.entity.UserCar;
import com.cmc.log.entity.UserEntity;
import com.cmc.log.enums.LogTypeEnum;
import com.cmc.log.util.LogThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:22
 */
@Service
@Slf4j
public class UserService extends ServiceImpl<UserDao, UserEntity> {

    @Autowired
    private UserDao userDao;

    @ServiceLog(serviceType = 1, mapper = "userDao",queryMethod = "queryByUserId",classType = UserCar.class, logType = {2})
    public void saveUser(UserCar userCar) {
        UserCar userCar1 = userDao.queryByUserId(1L);
        log.info("userCar1:{}",userCar1);
        LogThreadLocal.setThreadLocal(10L);
    }
}
