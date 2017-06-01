package com.quantour.ssm.test;

import com.quantour.ssm.dao.UserMapper;
import com.quantour.ssm.model.User;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by loohaze on 2017/6/1.
 */
public class UserTest {

    SqlSession session = FKSqlSessionFactory.getSqlSession();
    UserMapper userMapper = session.getMapper(UserMapper.class);

    public static void main(String[] args) {
        UserTest test = new UserTest();

//        test.testInsert();
//        test.testSelect();
//        test.testUpdate();
//        test.testInsertPassword();
//        test.testUpdatePassword();
//        test.testSelectPassword();
        test.testGetAllID();
    }

    public void testInsert(){
        User user = new User();

        user.setId("loohaze1");
        user.setUserName("HuangYan");
        user.setUserSex(0);
        user.setUserAge(20);
        user.setUserBirthday(Date.valueOf("1997-05-29"));
        user.setUserPhone("15895870061");
        user.setUserEmail("loohaze529@gmail.com");
        user.setUserAddress("Nanjing");
        user.setUserIntro("intro");

        System.out.println(user.getUserSex());
        userMapper.insert(user);

        session.commit();
    }

    public void testSelect(){
        User user = userMapper.selectByPrimaryKey("loohaze");
        System.out.println(user.getId());
        System.out.println(user.getUserAddress());
    }

    public void testUpdate(){
        User user = new User();

        user.setId("loohaze");
        user.setUserName("HuangYan");
        user.setUserSex(0);
        user.setUserAge(20);
        user.setUserBirthday(Date.valueOf("1997-05-29"));
        user.setUserPhone("15895870061");
        user.setUserEmail("loohaze529@gmail.com");
        user.setUserAddress("Nanjing");
        user.setUserIntro("introUpdate");

        userMapper.updateByPrimaryKey(user);

        session.commit();
    }

    public void testInsertPassword(){
        HashMap map = new HashMap();
        map.put("id","loohaze");
        map.put("password","12345678");

        userMapper.insertPassword(map);

        session.commit();
    }

    public void testUpdatePassword(){
        HashMap map = new HashMap();
        map.put("id","loohaze");
        map.put("password","87654321");

        userMapper.updatePassword(map);

        session.commit();
    }

    public void testSelectPassword(){
        String id = "loohaze";

        String password = userMapper.selectPassword(id);

        System.out.println(password);
    }

    public void testGetAllID(){
        List<String> list = userMapper.getAllUserId();
        for(String s : list){
            System.out.println(s);
        }
    }
}
