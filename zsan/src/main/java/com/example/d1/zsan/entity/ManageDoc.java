package com.example.d1.zsan.entity;

import java.util.List;

/**
 * Created by A on 2017/5/15.
 */

public class ManageDoc {

    private List<ListMagDoc> list;

    public List<ListMagDoc> getList() {
        return list;
    }

    public void setList(List<ListMagDoc> list) {
        this.list = list;
    }

    public static class ListMagDoc {
        /**
         * id : 1
         * name : 123123
         * pwd : 123123
         * sex : 男
         */

        private int id;
        private String name;
        private String pwd;
        private String sex;

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

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
