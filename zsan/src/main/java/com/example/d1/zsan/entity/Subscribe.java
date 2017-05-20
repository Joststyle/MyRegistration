package com.example.d1.zsan.entity;

import java.util.List;

/**
 * Created by A on 2017/5/15.
 */

public class Subscribe {

    private List<ListSub> list;

    public List<ListSub> getList() {
        return list;
    }

    public void setList(List<ListSub> list) {
        this.list = list;
    }

    public static class ListSub {
        /**
         * docname : 111111
         * id : 1
         * subscribe : 1
         * username : 111111
         */

        private String docname;
        private int id;
        private String subscribe;
        private String username;
        private boolean ischeck;

        public boolean isIscheck(){
            return ischeck;
        }

        public void setIscheck(boolean ischeck){
            this.ischeck = ischeck;
        }

        public String getDocname() {
            return docname;
        }

        public void setDocname(String docname) {
            this.docname = docname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(String subscribe) {
            this.subscribe = subscribe;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
