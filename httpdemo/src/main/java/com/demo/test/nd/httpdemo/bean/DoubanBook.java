package com.demo.test.nd.httpdemo.bean;

import java.util.List;

/**
 * Created by johnson on 2015/7/22.
 */
public class DoubanBook {

    /**
     * total : 239
     * books : [{"summary":"韩寒是一个不断创造奇迹的人。他在当代作家中的影响力已经毋庸置疑，即使一开始都被大家认为\u201c不务正业\u201d的赛车活动，时至今日他也用行动证明，自己可以做得很出色。中国的赛车运动总共就四个有分量的总冠军，韩寒已经拿到了其中三个，换句话说，他已经是职业赛车界名副其实的顶尖高手。七年之中，作者方肇一直在中国汽车运动的一线采访，见证了赛车手韩寒的每一个重要时刻。网络上被广泛转载的韩寒赛事消息多半出自方肇笔下。在多年的采访中，作者与韩寒结下了深厚的友情，因此萌生了为其写一本评传的想法。方肇文风犀利，幽默风趣，在文学批评方面也颇有建树，因此，无论是分析韩寒的赛车生涯还是写作，都非常中肯到位。此外，方肇的创作活动也得到了韩寒本人的大力支持，并正式授权。","tags":[{"title":"韩寒","count":184,"name":"韩寒"},{"title":"传记","count":75,"name":"传记"},{"title":"最好的年代","count":41,"name":"最好的年代"},{"title":"个人传记","count":22,"name":"个人传记"},{"title":"人物","count":16,"name":"人物"},{"title":"2012","count":10,"name":"2012"},{"title":"赛车","count":9,"name":"赛车"},{"title":"小说","count":9,"name":"小说"}],"origin_title":"","pubdate":"2012-1","catalog":"私奔\u2014\u2014韩寒\n危险驾驶\u2014\u2014方肇\n记一：浪子下山，韩少上道\n记二：人生不要太入戏\n记三：当上冠军还是小字辈\n记四：让梦想照进现实\n记五：开赛车的男人\n记六：一座城池，一地鸡毛\n记七：奇迹是写出来的，也是跑出来的\n记八：压力之下的光荣\n记九：无止境的单相思恋爱\n记十：公认的意见领袖\n记十一：遭遇双重门\n记十二：他的国\n记十三：虽千万人，吾往矣\n记十四：我们想跟世界谈谈 提问韩寒","author_intro":"方肇，著名赛车记者、足球评论家。曾供职：《中国青年报-青年体育报》、光线电视《体育界》、搜狐网体育频道、中青体育网赛车频道等，历任编辑、记者、总策划、副主编、频道总监等职。现为中青体育网赛车频道总监。著有：《风雨玫瑰》（与韩端合作）《中国越野英雄谱》《老兵孙福地和他的战友们》《越位》(上、下)","pages":"320","image":"http://img4.douban.com/mpic/s7029598.jpg","url":"http://api.douban.com/v2/book/10199324","publisher":"华文出版社","id":"10199324","author":["方肇"],"title":"韩寒","price":"38.90元","isbn10":"7507536157","translator":[],"alt":"http://book.douban.com/subject/10199324/","subtitle":"最好的年代","isbn13":"9787507536157","binding":"平装","images":{"small":"http://img4.douban.com/spic/s7029598.jpg","medium":"http://img4.douban.com/mpic/s7029598.jpg","large":"http://img4.douban.com/lpic/s7029598.jpg"},"rating":{"min":0,"max":10,"numRaters":345,"average":"6.9"},"alt_title":""}]
     * count : 1
     * start : 0
     */
    private int total;
    private List<BooksEntity> books;
    private int count;
    private int start;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setBooks(List<BooksEntity> books) {
        this.books = books;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public List<BooksEntity> getBooks() {
        return books;
    }

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public class BooksEntity {
        /**
         * summary : 韩寒是一个不断创造奇迹的人。他在当代作家中的影响力已经毋庸置疑，即使一开始都被大家认为“不务正业”的赛车活动，时至今日他也用行动证明，自己可以做得很出色。中国的赛车运动总共就四个有分量的总冠军，韩寒已经拿到了其中三个，换句话说，他已经是职业赛车界名副其实的顶尖高手。七年之中，作者方肇一直在中国汽车运动的一线采访，见证了赛车手韩寒的每一个重要时刻。网络上被广泛转载的韩寒赛事消息多半出自方肇笔下。在多年的采访中，作者与韩寒结下了深厚的友情，因此萌生了为其写一本评传的想法。方肇文风犀利，幽默风趣，在文学批评方面也颇有建树，因此，无论是分析韩寒的赛车生涯还是写作，都非常中肯到位。此外，方肇的创作活动也得到了韩寒本人的大力支持，并正式授权。
         * tags : [{"title":"韩寒","count":184,"name":"韩寒"},{"title":"传记","count":75,"name":"传记"},{"title":"最好的年代","count":41,"name":"最好的年代"},{"title":"个人传记","count":22,"name":"个人传记"},{"title":"人物","count":16,"name":"人物"},{"title":"2012","count":10,"name":"2012"},{"title":"赛车","count":9,"name":"赛车"},{"title":"小说","count":9,"name":"小说"}]
         * origin_title :
         * pubdate : 2012-1
         * catalog : 私奔——韩寒
         危险驾驶——方肇
         记一：浪子下山，韩少上道
         记二：人生不要太入戏
         记三：当上冠军还是小字辈
         记四：让梦想照进现实
         记五：开赛车的男人
         记六：一座城池，一地鸡毛
         记七：奇迹是写出来的，也是跑出来的
         记八：压力之下的光荣
         记九：无止境的单相思恋爱
         记十：公认的意见领袖
         记十一：遭遇双重门
         记十二：他的国
         记十三：虽千万人，吾往矣
         记十四：我们想跟世界谈谈 提问韩寒
         * author_intro : 方肇，著名赛车记者、足球评论家。曾供职：《中国青年报-青年体育报》、光线电视《体育界》、搜狐网体育频道、中青体育网赛车频道等，历任编辑、记者、总策划、副主编、频道总监等职。现为中青体育网赛车频道总监。著有：《风雨玫瑰》（与韩端合作）《中国越野英雄谱》《老兵孙福地和他的战友们》《越位》(上、下)
         * pages : 320
         * image : http://img4.douban.com/mpic/s7029598.jpg
         * url : http://api.douban.com/v2/book/10199324
         * publisher : 华文出版社
         * id : 10199324
         * author : ["方肇"]
         * title : 韩寒
         * price : 38.90元
         * isbn10 : 7507536157
         * translator : []
         * alt : http://book.douban.com/subject/10199324/
         * subtitle : 最好的年代
         * isbn13 : 9787507536157
         * binding : 平装
         * images : {"small":"http://img4.douban.com/spic/s7029598.jpg","medium":"http://img4.douban.com/mpic/s7029598.jpg","large":"http://img4.douban.com/lpic/s7029598.jpg"}
         * rating : {"min":0,"max":10,"numRaters":345,"average":"6.9"}
         * alt_title :
         */
        private String summary;
        private List<TagsEntity> tags;
        private String origin_title;
        private String pubdate;
        private String catalog;
        private String author_intro;
        private String pages;
        private String image;
        private String url;
        private String publisher;
        private String id;
        private List<String> author;
        private String title;
        private String price;
        private String isbn10;
        private List<?> translator;
        private String alt;
        private String subtitle;
        private String isbn13;
        private String binding;
        private ImagesEntity images;
        private RatingEntity rating;
        private String alt_title;

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public void setOrigin_title(String origin_title) {
            this.origin_title = origin_title;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }

        public void setAuthor_intro(String author_intro) {
            this.author_intro = author_intro;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setAuthor(List<String> author) {
            this.author = author;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setIsbn10(String isbn10) {
            this.isbn10 = isbn10;
        }

        public void setTranslator(List<?> translator) {
            this.translator = translator;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setIsbn13(String isbn13) {
            this.isbn13 = isbn13;
        }

        public void setBinding(String binding) {
            this.binding = binding;
        }

        public void setImages(ImagesEntity images) {
            this.images = images;
        }

        public void setRating(RatingEntity rating) {
            this.rating = rating;
        }

        public void setAlt_title(String alt_title) {
            this.alt_title = alt_title;
        }

        public String getSummary() {
            return summary;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public String getOrigin_title() {
            return origin_title;
        }

        public String getPubdate() {
            return pubdate;
        }

        public String getCatalog() {
            return catalog;
        }

        public String getAuthor_intro() {
            return author_intro;
        }

        public String getPages() {
            return pages;
        }

        public String getImage() {
            return image;
        }

        public String getUrl() {
            return url;
        }

        public String getPublisher() {
            return publisher;
        }

        public String getId() {
            return id;
        }

        public List<String> getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }

        public String getIsbn10() {
            return isbn10;
        }

        public List<?> getTranslator() {
            return translator;
        }

        public String getAlt() {
            return alt;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getIsbn13() {
            return isbn13;
        }

        public String getBinding() {
            return binding;
        }

        public ImagesEntity getImages() {
            return images;
        }

        public RatingEntity getRating() {
            return rating;
        }

        public String getAlt_title() {
            return alt_title;
        }

        public class TagsEntity {
            /**
             * title : 韩寒
             * count : 184
             * name : 韩寒
             */
            private String title;
            private int count;
            private String name;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public int getCount() {
                return count;
            }

            public String getName() {
                return name;
            }
        }

        public class ImagesEntity {
            /**
             * small : http://img4.douban.com/spic/s7029598.jpg
             * medium : http://img4.douban.com/mpic/s7029598.jpg
             * large : http://img4.douban.com/lpic/s7029598.jpg
             */
            private String small;
            private String medium;
            private String large;

            public void setSmall(String small) {
                this.small = small;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getSmall() {
                return small;
            }

            public String getMedium() {
                return medium;
            }

            public String getLarge() {
                return large;
            }
        }

        public class RatingEntity {
            /**
             * min : 0
             * max : 10
             * numRaters : 345
             * average : 6.9
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
