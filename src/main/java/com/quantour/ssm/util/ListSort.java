package com.quantour.ssm.util;

/**
 * Created by zhangzy on 2017/6/12.
 */
import java.text.SimpleDateFormat;
import java.util.*;

public class ListSort {
    public static class UserBean {
        private String id;
        private String birthday;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }

    public static void main(String[] args) {
        List<UserBean> list = new ArrayList<UserBean>();
        UserListGenerate(list);
        System.out.println("排序前：");

        for(int count=0;count<list.size();count++){
            System.out.println(list.get(count).getBirthday());
        }

        ListSort(list);
        System.out.println("排序后：");

        for(int count=0;count<list.size();count++){
            System.out.println(list.get(count).getBirthday());
        }
    }

    private static void UserListGenerate(List<UserBean> list) {
        UserBean user1 = new UserBean();
        UserBean user2 = new UserBean();
        UserBean user3 = new UserBean();
        user1.setId("zhagnsan");
        user1.setBirthday("2017-06-10 19:10:03");

        user2.setId("lisi");
        user2.setBirthday("2017-06-11 18:20:05");

        user3.setId("wangwu");
        user3.setBirthday("2017-06-10 18:20:20");

        list.add(user1);
        list.add(user2);
        list.add(user3);
    }

    private static void ListSort(List<UserBean> list) {
        Collections.sort(list, new Comparator<UserBean>() {
            @Override
            public int compare(UserBean o1, UserBean o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date dt1 = format.parse(o1.getBirthday());
                    Date dt2 = format.parse(o2.getBirthday());
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
}
