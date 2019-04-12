package com.pitt.patient.data;

public class RetrofitRespData {

//    private int respEvent;
//
//    private String seqId;
//
//    private String action;
//
//    private content content;
//
//    private static class content {
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String reminder;
        private String doctorId;
//    }

    //定义 输出返回数据 的方法
    public void show() {
//        System.out.println(respEvent);
//        System.out.println(seqId);
//        System.out.println(action);

        System.out.println(id);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(password);
        System.out.println(reminder);
        System.out.println(doctorId);
    }

}
