package com.demo.nd.test.bean;

/**
 * Created by Administrator on 2015/7/9.
 */
public class V2exHotBean {

    /**
     * member : {"avatar_normal":"//cdn.v2ex.co/avatar/4801/6f54/21824_normal.png?m=1433874837","id":21824,"username":"cevincheung","avatar_mini":"//cdn.v2ex.co/avatar/4801/6f54/21824_mini.png?m=1433874837","avatar_large":"//cdn.v2ex.co/avatar/4801/6f54/21824_large.png?m=1433874837","tagline":"蛋定的coder"}
     * content : ![](http://ww4.sinaimg.cn/mw1024/9093b87djw1etvsl09vc7j216o1kwtj6.jpg)
     * id : 204344
     * last_modified : 1436370024
     * node : {"avatar_normal":"//cdn.v2ex.co/navatar/c9f0/f895/8_normal.png?m=1436401308","id":8,"topics":2750,"title":"iPhone","title_alternative":"iPhone","name":"iphone","avatar_mini":"//cdn.v2ex.co/navatar/c9f0/f895/8_mini.png?m=1436401308","avatar_large":"//cdn.v2ex.co/navatar/c9f0/f895/8_large.png?m=1436401308","url":"http://www.v2ex.com/go/iphone"}
     * title : 苹果充电线容易断……所以买了一支胶棒
     * created : 1436370024
     * replies : 63
     * last_touched : 1436436606
     * url : http://www.v2ex.com/t/204344
     * content_rendered : <p><img src="http://ww4.sinaimg.cn/mw1024/9093b87djw1etvsl09vc7j216o1kwtj6.jpg" alt=""></p>

     */
    private MemberEntity member;
    private String content;
    private int id;
    private int last_modified;
    private NodeEntity node;
    private String title;
    private int created;
    private int replies;
    private int last_touched;
    private String url;
    private String content_rendered;

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLast_modified(int last_modified) {
        this.last_modified = last_modified;
    }

    public void setNode(NodeEntity node) {
        this.node = node;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public void setLast_touched(int last_touched) {
        this.last_touched = last_touched;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent_rendered(String content_rendered) {
        this.content_rendered = content_rendered;
    }

    public MemberEntity getMember() {
        return member;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public int getLast_modified() {
        return last_modified;
    }

    public NodeEntity getNode() {
        return node;
    }

    public String getTitle() {
        return title;
    }

    public int getCreated() {
        return created;
    }

    public int getReplies() {
        return replies;
    }

    public int getLast_touched() {
        return last_touched;
    }

    public String getUrl() {
        return url;
    }

    public String getContent_rendered() {
        return content_rendered;
    }

    public class MemberEntity {
        /**
         * avatar_normal : //cdn.v2ex.co/avatar/4801/6f54/21824_normal.png?m=1433874837
         * id : 21824
         * username : cevincheung
         * avatar_mini : //cdn.v2ex.co/avatar/4801/6f54/21824_mini.png?m=1433874837
         * avatar_large : //cdn.v2ex.co/avatar/4801/6f54/21824_large.png?m=1433874837
         * tagline : 蛋定的coder
         */
        private String avatar_normal;
        private int id;
        private String username;
        private String avatar_mini;
        private String avatar_large;
        private String tagline;

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setAvatar_mini(String avatar_mini) {
            this.avatar_mini = avatar_mini;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getAvatar_mini() {
            return avatar_mini;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public String getTagline() {
            return tagline;
        }
    }

    public class NodeEntity {
        /**
         * avatar_normal : //cdn.v2ex.co/navatar/c9f0/f895/8_normal.png?m=1436401308
         * id : 8
         * topics : 2750
         * title : iPhone
         * title_alternative : iPhone
         * name : iphone
         * avatar_mini : //cdn.v2ex.co/navatar/c9f0/f895/8_mini.png?m=1436401308
         * avatar_large : //cdn.v2ex.co/navatar/c9f0/f895/8_large.png?m=1436401308
         * url : http://www.v2ex.com/go/iphone
         */
        private String avatar_normal;
        private int id;
        private int topics;
        private String title;
        private String title_alternative;
        private String name;
        private String avatar_mini;
        private String avatar_large;
        private String url;

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTopics(int topics) {
            this.topics = topics;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTitle_alternative(String title_alternative) {
            this.title_alternative = title_alternative;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAvatar_mini(String avatar_mini) {
            this.avatar_mini = avatar_mini;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public int getId() {
            return id;
        }

        public int getTopics() {
            return topics;
        }

        public String getTitle() {
            return title;
        }

        public String getTitle_alternative() {
            return title_alternative;
        }

        public String getName() {
            return name;
        }

        public String getAvatar_mini() {
            return avatar_mini;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public String getUrl() {
            return url;
        }
    }
}
