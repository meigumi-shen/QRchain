package org.com.test;

import org.com.util;

public class testmain {
    public static void main(String[] args) {
//        testSQL testsql = new testSQL();
//        testsql.showDatabases();
//        SqlControl sql = new SqlControl();
//        sql.createDatabase("qrchain");
        testSerializable.student st = new testSerializable.student();
        st.setAge(19);
        st.setName("megumi");
        testSerializable.information inform = new testSerializable.information();
        inform.setWechatNumber("megumi Wechat");
        inform.setPhoneNumber("megumi Phone");
        st.setInform(inform);
        String str;
        str = util.objectToString(st);
        System.out.println(str);
        testSerializable.student st2 = (testSerializable.student)util.stringToObject(str);
        System.out.println(st2.getAge()
                + " "
                + st2.getName()
                + " "
                + st2.getInform().getPhoneNumber()
                + " "
                + st2.getInform().getWechatNumber()
        );
    }
}
