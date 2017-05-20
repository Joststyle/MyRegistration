package com.example.d1.zsan.entity;

import java.util.List;

/**
 * Created by A on 2017/5/16.
 */

public class Department {

    private List<ListDepart> list;

    public List<ListDepart> getList() {
        return list;
    }

    public void setList(List<ListDepart> list) {
        this.list = list;
    }

    public static class ListDepart {
        /**
         * function : 儿童疾病诊断
         * id : 0
         * name : 儿科
         * remark :
         * time : 2017-05-15
         */

        private String function;
        private int id;
        private String name;
        private String remark;
        private String time;

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
