package com.quantour.ssm.service;

import com.quantour.ssm.dto.userDTO;
import com.quantour.ssm.model.User;

import java.util.List;

/**
 * Created by loohaze on 2017/5/9.
 */
public interface UserService {

    public List<User> getAllUser();

    /**
     * 根据账号和密码来创建一个新的账号
     * @param account
     * @param password
     * @return
     */
    public boolean setNewAccount(String account,String password);

    /**
     * 根据账号判断这个账号是否存在
     * @param account
     * @return
     */
    public boolean isAccountValid(String account);

    /**
     * 根据账号和密码判断密码是否正确
     * @param account
     * @param password
     * @return
     */
    public boolean isPasswordValid(String account,String password);

    /**
     * 根据账号获取一个客户的账户信息
     * @param account
     * @return
     */
    public User getOneUserByAccount(String account);

    /**
     * 根据account和userdto更新该账号客户的账号信息
     * @param account
     * @param userdto
     * @return
     */
    public boolean updateUser(String account, userDTO userdto);

    /**
     * 如果新密码和旧密码相同则不能改变 并跳出提示
     * @param account
     * @param newPassword
     * @return
     */
    public boolean changeUserPassword(String account,String newPassword);

    


}
