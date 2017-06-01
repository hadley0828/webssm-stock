package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/5/25.
 */
public class userDTO {
    String account;
    String sex; //数据库中的是0男 1女 2保密
    int age;
    String birthday;
    String handsetNumber;
    String mail;
    String address;
    String introduction;

    public userDTO(){
        super();
    }

    @Override
    public String toString() {
        return "userDTO{" +
                "account='" + account + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", handsetNumber='" + handsetNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }





    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
