package com.cmc.log;

import com.cmc.log.entity.UserEntity;
import com.cmc.log.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LogDemoApplication.class)
class LogDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(1L);
        userEntity.setAddress("上海市");
        userEntity.setAge(19);
        userEntity.setJob("工作");
        userEntity.setName("小名2222");
        userService.saveUser(userEntity,"cmc");


    }

}
