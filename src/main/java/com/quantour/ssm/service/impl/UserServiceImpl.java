package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.UserMapper;
import com.quantour.ssm.dto.userDTO;
import com.quantour.ssm.model.User;
import com.quantour.ssm.service.UserService;
import com.quantour.ssm.util.DateConvert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
        ArrayList<String> allAccountList= (ArrayList<String>) userMapper.getAllUserId();

        List<User> allUserList=new ArrayList<User>();
        for(int count=0;count<allAccountList.size();count++){
            allUserList.add(userMapper.selectByPrimaryKey(allAccountList.get(count)));
        }

        return allUserList;
    }

    @Override
    public boolean setNewAccount(String account, String password) {
        ArrayList<String> allAccountList= (ArrayList<String>) userMapper.getAllUserId();

        if(allAccountList.contains(account)){
            return false;
        }else{
            User user = new User();
            user.setId(account);

            userMapper.insertSelective(user);

            HashMap map = new HashMap();
            map.put("id",account);
            map.put("password",password);
            userMapper.insertPassword(map);


            return true;
        }
    }

    @Override
    public boolean isAccountValid(String account) {
        ArrayList<String> allAccountList= (ArrayList<String>) userMapper.getAllUserId();
        if(allAccountList.contains(account)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isPasswordValid(String account, String password) {
        String passwordByAccount=userMapper.selectPassword(account);
        if(passwordByAccount.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public userDTO getOneUserByAccount(String account) {
        userDTO userdto=new userDTO();
        User user=userMapper.selectByPrimaryKey(account);

        userdto.setAccount(user.getId());

        if(user.getUserName()!=null){
            userdto.setName(user.getUserName());
        }
        if(user.getUserSex()!=null){
            if(user.getUserSex()==0){
                userdto.setSex("男");
            }else if(user.getUserSex()==1){
                userdto.setSex("女");
            }else{
                userdto.setSex("保密");
            }
        }
        if(user.getUserAge()!=null){
            userdto.setAge(user.getUserAge());
        }
        if(user.getUserBirthday()!=null){
            userdto.setBirthday(DateConvert.dateToString(user.getUserBirthday()));
        }
        if(user.getUserPhone()!=null){
            userdto.setHandsetNumber(user.getUserPhone());
        }
        if(user.getUserEmail()!=null){
            userdto.setMail(user.getUserEmail());
        }
        if(user.getUserAddress()!=null){
            userdto.setAddress(user.getUserAddress());
        }
        if(user.getUserIntro()!=null){
            userdto.setIntroduction(user.getUserIntro());
        }

        return userdto;
    }

    @Override
    public boolean updateUser(String account, userDTO userdto) {
        ArrayList<String> allAccountList= (ArrayList<String>) userMapper.getAllUserId();
        if (allAccountList.contains(account)){
            User user=new User();
            user.setId(userdto.getAccount());
            user.setUserAddress(userdto.getAddress());
            user.setUserAge(userdto.getAge());
            user.setUserBirthday(DateConvert.stringToDate(userdto.getBirthday()));  //userdto中的birthday形式要和date相同
            user.setUserEmail(userdto.getMail());
            user.setUserIntro(userdto.getIntroduction());
            user.setUserName(userdto.getName());
            user.setUserPhone(userdto.getHandsetNumber());

            String sexString=userdto.getSex();
            if(sexString.equals("男")){
                user.setUserSex(0);
            }else if(sexString.equals("女")){
                user.setUserSex(1);
            }else if(sexString.equals("保密")){
                user.setUserSex(2);
            }

            userMapper.updateByPrimaryKeySelective(user);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean changeUserPassword(String account, String newPassword) {
       ArrayList<String> allAccountList= (ArrayList<String>) userMapper.getAllUserId();
       if(allAccountList.contains(account)){

           HashMap map = new HashMap();
           map.put("id",account);
           map.put("password",newPassword);

           userMapper.updatePassword(map);

           return true;
       }else{
           return false;
       }
    }


}
