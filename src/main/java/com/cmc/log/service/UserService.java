package com.cmc.log.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.log.annotation.LogService;
import com.cmc.log.dao.UserDao;
import com.cmc.log.entity.UserEntity;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;


/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:22
 */
@Service
public class UserService extends ServiceImpl<UserDao,UserEntity> {


    @LogService(serviceType = 1,mapper = "userDao")
    public void saveUser(UserEntity userEntity,String userName){
        if (userEntity.getId()==null){
            baseMapper.insert(userEntity);
        }else {
            baseMapper.updateById(userEntity);
        }
    }
}
