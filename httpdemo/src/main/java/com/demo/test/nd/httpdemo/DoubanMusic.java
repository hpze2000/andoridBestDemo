package com.demo.test.nd.httpdemo;

import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
public class DoubanMusic {


    /**
     * musics : [{"id":"1403307","tags":[{"count":15810,"name":"周杰伦"},{"count":6039,"name":"范特西"},{"count":4562,"name":"台湾"},{"count":3581,"name":"Jay"},{"count":3120,"name":"R&B"},{"count":2201,"name":"流行"},{"count":1870,"name":"华语"},{"count":1755,"name":"pop"}],"mobile_link":"http://m.douban.com/music/subject/1403307/","author":[{"name":"周杰伦"}],"title":"范特西","alt":"http://music.douban.com/subject/1403307/","attrs":{"title":["范特西"],"discs":["1"],"pubdate":["2001"],"singer":["周杰伦"],"tracks":["1. 爱在西元前\n2. 爸我回来了\n3. 简单爱\n4. 忍者\n5. 开不了口\n6. 上海一九四三\n7. 对不起\n8. 威廉古堡\n9. 双截棍\n10. 安静"],"media":["CD"],"version":["专辑"],"publisher":["BMG"]},"image":"http://img3.douban.com/spic/s3750422.jpg","rating":{"min":0,"max":10,"numRaters":64544,"average":"9.2"},"alt_title":"Fantasy"}]
     * total : 256
     * count : 1
     * start : 0
     */
    private List<MusicsEntity> musics;
    private int total;
    private int count;
    private int start;

    public void setMusics(List<MusicsEntity> musics) {
        this.musics = musics;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<MusicsEntity> getMusics() {
        return musics;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public static class MusicsEntity {
        /**
         * id : 1403307
         * tags : [{"count":15810,"name":"周杰伦"},{"count":6039,"name":"范特西"},{"count":4562,"name":"台湾"},{"count":3581,"name":"Jay"},{"count":3120,"name":"R&B"},{"count":2201,"name":"流行"},{"count":1870,"name":"华语"},{"count":1755,"name":"pop"}]
         * mobile_link : http://m.douban.com/music/subject/1403307/
         * author : [{"name":"周杰伦"}]
         * title : 范特西
         * alt : http://music.douban.com/subject/1403307/
         * attrs : {"title":["范特西"],"discs":["1"],"pubdate":["2001"],"singer":["周杰伦"],"tracks":["1. 爱在西元前\n2. 爸我回来了\n3. 简单爱\n4. 忍者\n5. 开不了口\n6. 上海一九四三\n7. 对不起\n8. 威廉古堡\n9. 双截棍\n10. 安静"],"media":["CD"],"version":["专辑"],"publisher":["BMG"]}
         * image : http://img3.douban.com/spic/s3750422.jpg
         * rating : {"min":0,"max":10,"numRaters":64544,"average":"9.2"}
         * alt_title : Fantasy
         */
        private String id;
        private List<TagsEntity> tags;
        private String mobile_link;
        private List<TagsEntity> author;
        private String title;
        private String alt;
        private AttrsEntity attrs;
        private String image;
        private RatingEntity rating;
        private String alt_title;

        public void setId(String id) {
            this.id = id;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public void setMobile_link(String mobile_link) {
            this.mobile_link = mobile_link;
        }

        public void setAuthor(List<TagsEntity> author) {
            this.author = author;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setAttrs(AttrsEntity attrs) {
            this.attrs = attrs;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setRating(RatingEntity rating) {
            this.rating = rating;
        }

        public void setAlt_title(String alt_title) {
            this.alt_title = alt_title;
        }

        public String getId() {
            return id;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public String getMobile_link() {
            return mobile_link;
        }

        public List<TagsEntity> getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getAlt() {
            return alt;
        }

        public AttrsEntity getAttrs() {
            return attrs;
        }

        public String getImage() {
            return image;
        }

        public RatingEntity getRating() {
            return rating;
        }

        public String getAlt_title() {
            return alt_title;
        }

        public static class TagsEntity {
            /**
             * count : 15810
             * name : 周杰伦
             */
            private int count;
            private String name;

            public void setCount(int count) {
                this.count = count;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCount() {
                return count;
            }

            public String getName() {
                return name;
            }
        }

        public static class AttrsEntity {
            /**
             * title : ["范特西"]
             * discs : ["1"]
             * pubdate : ["2001"]
             * singer : ["周杰伦"]
             * tracks : ["1. 爱在西元前\n2. 爸我回来了\n3. 简单爱\n4. 忍者\n5. 开不了口\n6. 上海一九四三\n7. 对不起\n8. 威廉古堡\n9. 双截棍\n10. 安静"]
             * media : ["CD"]
             * version : ["专辑"]
             * publisher : ["BMG"]
             */
            private List<String> title;
            private List<String> discs;
            private List<String> pubdate;
            private List<String> singer;
            private List<String> tracks;
            private List<String> media;
            private List<String> version;
            private List<String> publisher;

            public void setTitle(List<String> title) {
                this.title = title;
            }

            public void setDiscs(List<String> discs) {
                this.discs = discs;
            }

            public void setPubdate(List<String> pubdate) {
                this.pubdate = pubdate;
            }

            public void setSinger(List<String> singer) {
                this.singer = singer;
            }

            public void setTracks(List<String> tracks) {
                this.tracks = tracks;
            }

            public void setMedia(List<String> media) {
                this.media = media;
            }

            public void setVersion(List<String> version) {
                this.version = version;
            }

            public void setPublisher(List<String> publisher) {
                this.publisher = publisher;
            }

            public List<String> getTitle() {
                return title;
            }

            public List<String> getDiscs() {
                return discs;
            }

            public List<String> getPubdate() {
                return pubdate;
            }

            public List<String> getSinger() {
                return singer;
            }

            public List<String> getTracks() {
                return tracks;
            }

            public List<String> getMedia() {
                return media;
            }

            public List<String> getVersion() {
                return version;
            }

            public List<String> getPublisher() {
                return publisher;
            }
        }

        public static class RatingEntity {
            /**
             * min : 0
             * max : 10
             * numRaters : 64544
             * average : 9.2
             */
            private int min;
            private int max;
            private int numRaters;
            private String average;

            public void setMin(int min) {
                this.min = min;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public void setNumRaters(int numRaters) {
                this.numRaters = numRaters;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public int getMin() {
                return min;
            }

            public int getMax() {
                return max;
            }

            public int getNumRaters() {
                return numRaters;
            }

            public String getAverage() {
                return average;
            }
        }
    }
}
