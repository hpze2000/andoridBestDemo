package com.demo.nd.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2015/7/8.
 */
public class Bean implements Parcelable {

    /**
     * message : success
     * status : 200
     * data : {"series":[{"showAllSymbol":true,"name":"WAF,【bio】匿名访问,并发0","data":["0"],"type":"line"}],"legend":["WAF,【bio】匿名访问,并发0"],"xAxisData":["2015-06-30"]}
     */
    private String message;
    private int status;
    private DataEntity data;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public class DataEntity {
        /**
         * series : [{"showAllSymbol":true,"name":"WAF,【bio】匿名访问,并发0","data":["0"],"type":"line"}]
         * legend : ["WAF,【bio】匿名访问,并发0"]
         * xAxisData : ["2015-06-30"]
         */
        private List<SeriesEntity> series;
        private List<String> legend;
        private List<String> xAxisData;

        public void setSeries(List<SeriesEntity> series) {
            this.series = series;
        }

        public void setLegend(List<String> legend) {
            this.legend = legend;
        }

        public void setXAxisData(List<String> xAxisData) {
            this.xAxisData = xAxisData;
        }

        public List<SeriesEntity> getSeries() {
            return series;
        }

        public List<String> getLegend() {
            return legend;
        }

        public List<String> getXAxisData() {
            return xAxisData;
        }

        public class SeriesEntity {
            /**
             * showAllSymbol : true
             * name : WAF,【bio】匿名访问,并发0
             * data : ["0"]
             * type : line
             */
            private boolean showAllSymbol;
            private String name;
            private List<String> data;
            private String type;

            public void setShowAllSymbol(boolean showAllSymbol) {
                this.showAllSymbol = showAllSymbol;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setData(List<String> data) {
                this.data = data;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isShowAllSymbol() {
                return showAllSymbol;
            }

            public String getName() {
                return name;
            }

            public List<String> getData() {
                return data;
            }

            public String getType() {
                return type;
            }
        }
    }
}
