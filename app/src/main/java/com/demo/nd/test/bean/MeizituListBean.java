package com.demo.nd.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2015/7/13.
 */
public class MeizituListBean implements Parcelable {

    /**
     * ret : 200
     * data : {"totalNum":"38915","pageSize":1,"list":[{"id":"286522","picPath":"http://images.meitu.tv/1_73968914/s_p28363510.jpg","title":"饿哭了 真的","totalImgCount":"1"}],"totalPage":38915,"pageNum":0}
     * msg :
     */
    private int ret;
    private DataEntity data;
    private String msg;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }


    public class DataEntity {
        /**
         * totalNum : 38915
         * pageSize : 1
         * list : [{"id":"286522","picPath":"http://images.meitu.tv/1_73968914/s_p28363510.jpg","title":"饿哭了 真的","totalImgCount":"1"}]
         * totalPage : 38915
         * pageNum : 0
         */
        private String totalNum;
        private int pageSize;
        private List<ListEntity> list;
        private int totalPage;
        private int pageNum;

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public String getTotalNum() {
            return totalNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public int getPageNum() {
            return pageNum;
        }

        public class ListEntity {
            /**
             * id : 286522
             * picPath : http://images.meitu.tv/1_73968914/s_p28363510.jpg
             * title : 饿哭了 真的
             * totalImgCount : 1
             */
            private String id;
            private String picPath;
            private String title;
            private String totalImgCount;

            public void setId(String id) {
                this.id = id;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTotalImgCount(String totalImgCount) {
                this.totalImgCount = totalImgCount;
            }

            public String getId() {
                return id;
            }

            public String getPicPath() {
                return picPath;
            }

            public String getTitle() {
                return title;
            }

            public String getTotalImgCount() {
                return totalImgCount;
            }
        }
    }
}
