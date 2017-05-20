package com.example.d1.zsan.entity;

import java.util.List;

/**
 * Created by A on 2017/5/3.
 */

public class HomeEntity {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 0
         * information : 骨质增生要吃什么
         */

        private int id;
        private String information;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public String getContent(){return content;}

        public void setContent(String content){ this.content=content;}
    }
}
