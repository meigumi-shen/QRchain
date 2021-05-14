package org.com.test;

import java.io.Serializable;

public class testSerializable {
    public static class student implements Serializable {
        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public information getInform() {
            return inform;
        }

        public void setInform(information inform) {
            this.inform = inform;
        }

        information inform;
        public String toString(){
            return this.name+this.age+this.inform.toString();
        }
    }
    public static class information implements Serializable{
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        String phoneNumber;

        public String getWechatNumber() {
            return wechatNumber;
        }

        public void setWechatNumber(String wechatNumber) {
            this.wechatNumber = wechatNumber;
        }

        String wechatNumber;
        public String toString(){
            return this.phoneNumber+this.wechatNumber;
        }
    }
}
