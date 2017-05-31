package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.UserMapper;
import com.quantour.ssm.model.User;
import com.quantour.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by loohaze on 2017/5/9.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }


}
