package com.cmc.log.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.log.annotation.ServiceLog;
import com.cmc.log.dao.UserDao;
import com.cmc.log.entity.UserCar;
import com.cmc.log.entity.UserEntity;
import com.cmc.log.enums.LogTypeEnum;
import com.cmc.log.util.LogThreadLocal;
import org.springframework.stereotype.Service;


/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:22
 */
@Service
public class UserService extends ServiceImpl<UserDao, UserEntity> {

    public static final ThreadLocal<Long> t=new ThreadLocal<>();

    @ServiceLog(serviceType = 1, mapper = "userDao", classType = UserCar.class, logType = {1})
    public void saveUser(UserCar userCar) {
        LogThreadLocal.setThreadLocal(10L);
    }
}
