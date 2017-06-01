package com.quantour.ssm.dao;

import com.quantour.ssm.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserMapper {
    public void deleteByPrimaryKey(String id);

    public void insert(User record);

    public int insertSelective(User record);

    public User selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(User record);

    public int updateByPrimaryKey(User record);


    public void deletePassword(String id);

    public void insertPassword(HashMap map);

    public void updatePassword(HashMap map);

    public String selectPassword(String id);


    public List<String> getAllUserId();

}