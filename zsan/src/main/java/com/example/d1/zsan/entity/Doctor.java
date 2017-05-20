package com.example.d1.zsan.entity;

import java.util.List;

/**
 * Created by A on 2017/5/10.
 */

public class Doctor {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * department : 心血管内科
         * describe : 多年的临床经验，平时看病不太依靠高科技仪器。因为慕名找上门的病人多，胡佩兰每天都会坚持看完所有病人才下班，对患者也极有耐心，给病人开药，很少超过一百元。
         * doc : 123456
         * hospital : 北京天坛医院
         * id : 0
         * name : 胡佩兰
         * pas : 123456
         * visits : 0
         */

        private String department;
        private String describe;
        private String doc;
        private String hospital;
        private int id;
        private String name;
        private String pas;
        private int visits;

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getDoc() {
            return doc;
        }

        public void setDoc(String doc) {
            this.doc = doc;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPas() {
            return pas;
        }

        public void setPas(String pas) {
            this.pas = pas;
        }

        public int getVisits() {
            return visits;
        }

        public void setVisits(int visits) {
            this.visits = visits;
        }
    }
}
