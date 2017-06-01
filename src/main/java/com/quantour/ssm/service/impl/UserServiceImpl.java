package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.UserMapper;
import com.quantour.ssm.dto.userDTO;
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

    @Override
    public boolean isAccountValid(String account) {
        return false;
    }

    @Override
    public boolean isPasswordValid(String account, String password) {
        return false;
    }

    @Override
    public User getOneUserByAccount(String account) {
        return null;
    }

    @Override
    public boolean updateUser(String account, userDTO userdto) {
        return false;
    }

    @Override
    public boolean changeUserPassword(String account, String newPassword) {
        return false;
    }


}
