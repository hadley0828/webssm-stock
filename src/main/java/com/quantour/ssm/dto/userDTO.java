package com.quantour.ssm.dto;

import com.quantour.ssm.model.User;

/**
 * Created by zhangzy on 2017/5/25.
 */
public class userDTO {
    String account;
    String name;
    String sex; //数据库中的是0男 1女 2保密
    int age;
    String birthday;
    String handsetNumber;
    String mail;
    String address;
    String introduction;

    @Override
    public String toString() {
        return "userDTO{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", handsetNumber='" + handsetNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public userDTO(){
        super();
    }

    public userDTO(User user){
        this.account=user.getId();
//        this.name=user.getUserName();
//        if(user.getUserSex()==0){
//            this.sex="男";
//        }else if(user.getUserSex()==1){
//            this.sex="女";
//        }else if(user.getUserSex()==2){
//            this.sex="保密";
//        }else if(user.getUserSex() == null){
//            this.sex = null;
//        }
//        this.age=user.getUserAge();
//        this.birthday= DateConvert.dateToString(user.getUserBirthday());
//        this.handsetNumber=user.getUserPhone();
//        this.mail=user.getUserEmail();
//        this.address=user.getUserAddress();
//        this.introduction=user.getUserIntro();
    }




    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHandsetNumber() {
        return handsetNumber;
    }

    public void setHandsetNumber(String handsetNumber) {
        this.handsetNumber = handsetNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }




}
