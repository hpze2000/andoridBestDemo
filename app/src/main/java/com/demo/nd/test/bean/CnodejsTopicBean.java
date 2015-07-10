package com.demo.nd.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2015/7/10.
 */
public class CnodejsTopicBean implements Parcelable {
    /**
     * data : {"visit_count":682,"content":"<div class=\"markdown-text\"><h2>前言<\/h2>\n<p>业余时间写的，目前只有IOS版本(安卓版本等React-Native发布安卓版本后会跟进)。\n目前处于开发阶段，bug很多，还有各种事情要做。各位坛友可以clone下来跑在iphone上试试。\n目前代码有点乱，各位客官先看着。哈哈。\n有iPhone的同学可以在手机上安一个（方法我后面会提到），然后没事就可以用这个暂且回帖，并顺便帮忙测试嘛。<\/p>\n<h2>仓库地址<\/h2>\n<p><a href=\"https://github.com/soliury/noder-react-native\">https://github.com/soliury/noder-react-native<\/a><\/p>\n<h2>功能演示<\/h2>\n<p><img src=\"http://7lrzfj.com1.z0.glb.clouddn.com/soliurynoder.gif\" alt><\/p>\n<h2>目前已有功能<\/h2>\n<ul>\n<li>回帖<\/li>\n<li>查看帖子<\/li>\n<li>查看用户信息<\/li>\n<li>查看消息<\/li>\n<\/ul>\n<h2>未实现的功能<\/h2>\n<ul>\n<li>发帖<\/li>\n<li>点赞，根据点赞的个数置顶回帖<\/li>\n<li>推送<\/li>\n<\/ul>\n<h2>TODO LIST<\/h2>\n<ul>\n<li>修复各种小bugs<\/li>\n<li>添加点赞<\/li>\n<li>添加设置页面<\/li>\n<li>添加登出功能<\/li>\n<li>添加推送<\/li>\n<li>添加关于页面<\/li>\n<li>将HTML渲染功能单独分离出来为一个模块，解决渲染很慢的问题<\/li>\n<li>等0.7.0正式版出来以后，寻求一个更好的不同Scene之间通信的方式<\/li>\n<li>重构代码（现在的代码真的很乱，很乱）<\/li>\n<li>添加测试<\/li>\n<li>上线到app Store<\/li>\n<\/ul>\n<h2>试用<\/h2>\n<pre class=\"prettyprint\"><code>git clone https://github.com/soliury/noder-react-native.git\nnpm install\n<\/code><\/pre><p>然后在Xcode中点击运行。<\/p>\n<p>想要跑在iphone上，需要运行：<\/p>\n<pre class=\"prettyprint\"><code>    gulp replace\n<\/code><\/pre><p>这样可以自动将js的获取地址改为电脑ip<\/p>\n<p>然后按照react-native<a href=\"http://facebook.github.io/react-native/docs/runningondevice.html#content\">官方文档<\/a>设置即可运行在手机上。<\/p>\n<p>当然我现在更加希望坛友如果有Iphone，可以跑一个离线build版本，这样地铁上也可以用一用，然后还可以顺便帮忙测试一下用起来如何。<\/p>\n<h3>如何做<\/h3>\n<ul>\n<li>打开<code>iOS/AppDelegate.m<\/code><\/li>\n<li>将<code>jsCodeLocation = [[NSBundle mainBundle]<\/code>这行代码的注释去掉<\/li>\n<li>运行<code>react-native bundle<\/code><\/li>\n<\/ul>\n<h3>出现错误可以尝试以下解决办法<\/h3>\n<ul>\n<li>重新编译所有的依赖的库（这里指oc的库，不是js）<\/li>\n<li>肯能有些module之间会有冲突，比如<code>react-native-overlay<\/code>和<code>react-native-modal<\/code>，可以删除掉冲突的文件<\/li>\n<li>实在不行，就提个issue吧<\/li>\n<\/ul>\n<h2>已知BUG<\/h2>\n<ul>\n<li>在不同的scene之间传递消息，目前没有很好地解决办法，现在用的办法都好蛋疼，等0.7.0正式版出来以后<code>Navigator<\/code>可以更好地控制<code>willFocus<\/code>，更好地添加事件，等出来以后再完善吧。<\/li>\n<\/ul>\n<\/div>","id":"559bd1b91e5c761761468884","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"title":"React-Native 客户端【开坑】【求issue】","last_reply_at":"2015-07-09T15:23:18.063Z","reply_count":16,"replies":[{"content":"<div class=\"markdown-text\"><p>不错，顶起<\/p>\n<\/div>","id":"559bd4ec1e5c761761468885","author":{"loginname":"shinygang","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F7077490%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T13:32:28.685Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>不错，赞一个！<\/p>\n<\/div>","id":"559bd74d1e5c76176146888e","author":{"loginname":"In37","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F6575161%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T13:42:37.040Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>赞\n出个包 放到fir.im上 用企业证书打包<\/p>\n<\/div>","id":"559bd80c1e5c761761468894","author":{"loginname":"ilanceli","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F874744%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T13:45:48.134Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/ilanceli\">@ilanceli<\/a> 对了，我们两个可以共用一个推送不勒<\/p>\n<p>From <a href=\"https://github.com/soliury/noder-react-native\">Noder<\/a><\/p>\n<\/div>","id":"559bdd0e1e5c761761468899","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T14:07:10.849Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/ilanceli\">@ilanceli<\/a> 企业证书啊，看来我得向公司要一个<\/p>\n<p>From <a href=\"https://github.com/soliury/noder-react-native\">Noder<\/a><\/p>\n<\/div>","id":"559bea341e5c7617614688ae","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T15:03:16.375Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/ilanceli\">@ilanceli<\/a> 请问企业证书有什么好处呢\n自豪地采用 <a href=\"https://github.com/lanceli/cnodejs-ionic\">CNodeJS ionic<\/a><\/p>\n<\/div>","id":"559bfef91e5c7617614688bb","author":{"loginname":"gantoday","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F7780154%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T16:31:53.753Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>顶<\/p>\n<\/div>","id":"559c5d5c1e5c7617614688d5","author":{"loginname":"Dengshen","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F10645383%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T23:14:36.918Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/Dengshen\">@Dengshen<\/a> 你这id好熟悉，是不是我认识你？<\/p>\n<p>From <a href=\"https://github.com/soliury/noder-react-native\">Noder<\/a><\/p>\n<\/div>","id":"559cb4dff58819b16b5a7344","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T05:27:59.397Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>可以打包ipa么<\/p>\n<\/div>","id":"559cdf07f58819b16b5a737d","author":{"loginname":"wandergis","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5799374%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T08:27:51.646Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/wandergis\">@wandergis<\/a> 稍等吧，我去搞一个企业证书<\/p>\n<p>From <a href=\"https://github.com/soliury/noder-react-native\">Noder<\/a><\/p>\n<\/div>","id":"559ced493d79442724b9f060","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T09:28:41.010Z","ups":["5396caeca087f45620dd741a"]},{"content":"<div class=\"markdown-text\"><p>顶顶顶！<\/p>\n<\/div>","id":"559d00b63d79442724b9f070","author":{"loginname":"huanglong","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F670360%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T10:51:34.464Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>32个赞！<\/p>\n<\/div>","id":"559d34503d79442724b9f0a4","author":{"loginname":"yidahis","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F6274202%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T14:31:44.009Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>为啥我用xcode打开之后是空白的。。。<\/p>\n<\/div>","id":"559e431a3d79442724b9f1a7","author":{"loginname":"amoa400","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F2351869%3Fv%3D3%26s%3D120"},"create_at":"2015-07-09T09:47:06.939Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>原来是IP地址不对，改了之后就好了<\/p>\n<\/div>","id":"559e44ec3d79442724b9f1ab","author":{"loginname":"amoa400","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F2351869%3Fv%3D3%26s%3D120"},"create_at":"2015-07-09T09:54:52.902Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>安卓的什么时候出<\/p>\n<\/div>","id":"559e91c83d79442724b9f1de","author":{"loginname":"ystyle","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F4478635%3Fv%3D3%26s%3D120"},"create_at":"2015-07-09T15:22:48.733Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/ystyle\">@ystyle<\/a> 是 React-Native什么出安卓版<\/p>\n<\/div>","id":"559e91e63d79442724b9f1e0","author":{"loginname":"ystyle","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F4478635%3Fv%3D3%26s%3D120"},"create_at":"2015-07-09T15:23:18.055Z","ups":[]}],"create_at":"2015-07-07T13:18:49.805Z","tab":"share","good":false,"author_id":"51ed5627f4963ade0ea60395","top":true}
     */
    private DataEntity data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public DataEntity getData() {
        return data;
    }


    public class DataEntity {
        /**
         * visit_count : 682
         * content : <div class="markdown-text"><h2>前言</h2>
         <p>业余时间写的，目前只有IOS版本(安卓版本等React-Native发布安卓版本后会跟进)。
         目前处于开发阶段，bug很多，还有各种事情要做。各位坛友可以clone下来跑在iphone上试试。
         目前代码有点乱，各位客官先看着。哈哈。
         有iPhone的同学可以在手机上安一个（方法我后面会提到），然后没事就可以用这个暂且回帖，并顺便帮忙测试嘛。</p>
         <h2>仓库地址</h2>
         <p><a href="https://github.com/soliury/noder-react-native">https://github.com/soliury/noder-react-native</a></p>
         <h2>功能演示</h2>
         <p><img src="http://7lrzfj.com1.z0.glb.clouddn.com/soliurynoder.gif" alt></p>
         <h2>目前已有功能</h2>
         <ul>
         <li>回帖</li>
         <li>查看帖子</li>
         <li>查看用户信息</li>
         <li>查看消息</li>
         </ul>
         <h2>未实现的功能</h2>
         <ul>
         <li>发帖</li>
         <li>点赞，根据点赞的个数置顶回帖</li>
         <li>推送</li>
         </ul>
         <h2>TODO LIST</h2>
         <ul>
         <li>修复各种小bugs</li>
         <li>添加点赞</li>
         <li>添加设置页面</li>
         <li>添加登出功能</li>
         <li>添加推送</li>
         <li>添加关于页面</li>
         <li>将HTML渲染功能单独分离出来为一个模块，解决渲染很慢的问题</li>
         <li>等0.7.0正式版出来以后，寻求一个更好的不同Scene之间通信的方式</li>
         <li>重构代码（现在的代码真的很乱，很乱）</li>
         <li>添加测试</li>
         <li>上线到app Store</li>
         </ul>
         <h2>试用</h2>
         <pre class="prettyprint"><code>git clone https://github.com/soliury/noder-react-native.git
         npm install
         </code></pre><p>然后在Xcode中点击运行。</p>
         <p>想要跑在iphone上，需要运行：</p>
         <pre class="prettyprint"><code>    gulp replace
         </code></pre><p>这样可以自动将js的获取地址改为电脑ip</p>
         <p>然后按照react-native<a href="http://facebook.github.io/react-native/docs/runningondevice.html#content">官方文档</a>设置即可运行在手机上。</p>
         <p>当然我现在更加希望坛友如果有Iphone，可以跑一个离线build版本，这样地铁上也可以用一用，然后还可以顺便帮忙测试一下用起来如何。</p>
         <h3>如何做</h3>
         <ul>
         <li>打开<code>iOS/AppDelegate.m</code></li>
         <li>将<code>jsCodeLocation = [[NSBundle mainBundle]</code>这行代码的注释去掉</li>
         <li>运行<code>react-native bundle</code></li>
         </ul>
         <h3>出现错误可以尝试以下解决办法</h3>
         <ul>
         <li>重新编译所有的依赖的库（这里指oc的库，不是js）</li>
         <li>肯能有些module之间会有冲突，比如<code>react-native-overlay</code>和<code>react-native-modal</code>，可以删除掉冲突的文件</li>
         <li>实在不行，就提个issue吧</li>
         </ul>
         <h2>已知BUG</h2>
         <ul>
         <li>在不同的scene之间传递消息，目前没有很好地解决办法，现在用的办法都好蛋疼，等0.7.0正式版出来以后<code>Navigator</code>可以更好地控制<code>willFocus</code>，更好地添加事件，等出来以后再完善吧。</li>
         </ul>
         </div>
         * id : 559bd1b91e5c761761468884
         * author : {"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"}
         * title : React-Native 客户端【开坑】【求issue】
         * last_reply_at : 2015-07-09T15:23:18.063Z
         * reply_count : 16
         * replies : [{"content":"<div class=\"markdown-text\"><p>不错，顶起<\/p>\n<\/div>","id":"559bd4ec1e5c761761468885","author":{"loginname":"shinygang","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F7077490%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T13:32:28.685Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>不错，赞一个！<\/p>\n<\/div>","id":"559bd74d1e5c76176146888e","author":{"loginname":"In37","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F6575161%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T13:42:37.040Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>赞\n出个包 放到fir.im上 用企业证书打包<\/p>\n<\/div>","id":"559bd80c1e5c761761468894","author":{"loginname":"ilanceli","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F874744%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T13:45:48.134Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/ilanceli\">@ilanceli<\/a> 对了，我们两个可以共用一个推送不勒<\/p>\n<p>From <a href=\"https://github.com/soliury/noder-react-native\">Noder<\/a><\/p>\n<\/div>","id":"559bdd0e1e5c761761468899","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T14:07:10.849Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/ilanceli\">@ilanceli<\/a> 企业证书啊，看来我得向公司要一个<\/p>\n<p>From <a href=\"https://github.com/soliury/noder-react-native\">Noder<\/a><\/p>\n<\/div>","id":"559bea341e5c7617614688ae","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T15:03:16.375Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/ilanceli\">@ilanceli<\/a> 请问企业证书有什么好处呢\n自豪地采用 <a href=\"https://github.com/lanceli/cnodejs-ionic\">CNodeJS ionic<\/a><\/p>\n<\/div>","id":"559bfef91e5c7617614688bb","author":{"loginname":"gantoday","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F7780154%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T16:31:53.753Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>顶<\/p>\n<\/div>","id":"559c5d5c1e5c7617614688d5","author":{"loginname":"Dengshen","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F10645383%3Fv%3D3%26s%3D120"},"create_at":"2015-07-07T23:14:36.918Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/Dengshen\">@Dengshen<\/a> 你这id好熟悉，是不是我认识你？<\/p>\n<p>From <a href=\"https://github.com/soliury/noder-react-native\">Noder<\/a><\/p>\n<\/div>","id":"559cb4dff58819b16b5a7344","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T05:27:59.397Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>可以打包ipa么<\/p>\n<\/div>","id":"559cdf07f58819b16b5a737d","author":{"loginname":"wandergis","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5799374%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T08:27:51.646Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/wandergis\">@wandergis<\/a> 稍等吧，我去搞一个企业证书<\/p>\n<p>From <a href=\"https://github.com/soliury/noder-react-native\">Noder<\/a><\/p>\n<\/div>","id":"559ced493d79442724b9f060","author":{"loginname":"soliury","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T09:28:41.010Z","ups":["5396caeca087f45620dd741a"]},{"content":"<div class=\"markdown-text\"><p>顶顶顶！<\/p>\n<\/div>","id":"559d00b63d79442724b9f070","author":{"loginname":"huanglong","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F670360%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T10:51:34.464Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>32个赞！<\/p>\n<\/div>","id":"559d34503d79442724b9f0a4","author":{"loginname":"yidahis","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F6274202%3Fv%3D3%26s%3D120"},"create_at":"2015-07-08T14:31:44.009Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>为啥我用xcode打开之后是空白的。。。<\/p>\n<\/div>","id":"559e431a3d79442724b9f1a7","author":{"loginname":"amoa400","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F2351869%3Fv%3D3%26s%3D120"},"create_at":"2015-07-09T09:47:06.939Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>原来是IP地址不对，改了之后就好了<\/p>\n<\/div>","id":"559e44ec3d79442724b9f1ab","author":{"loginname":"amoa400","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F2351869%3Fv%3D3%26s%3D120"},"create_at":"2015-07-09T09:54:52.902Z","ups":[]},{"content":"<div class=\"markdown-text\"><p>安卓的什么时候出<\/p>\n<\/div>","id":"559e91c83d79442724b9f1de","author":{"loginname":"ystyle","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F4478635%3Fv%3D3%26s%3D120"},"create_at":"2015-07-09T15:22:48.733Z","ups":[]},{"content":"<div class=\"markdown-text\"><p><a href=\"/user/ystyle\">@ystyle<\/a> 是 React-Native什么出安卓版<\/p>\n<\/div>","id":"559e91e63d79442724b9f1e0","author":{"loginname":"ystyle","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F4478635%3Fv%3D3%26s%3D120"},"create_at":"2015-07-09T15:23:18.055Z","ups":[]}]
         * create_at : 2015-07-07T13:18:49.805Z
         * tab : share
         * good : false
         * author_id : 51ed5627f4963ade0ea60395
         * top : true
         */
        private int visit_count;
        private String content;
        private String id;
        private AuthorEntity author;
        private String title;
        private String last_reply_at;
        private int reply_count;
        private List<RepliesEntity> replies;
        private String create_at;
        private String tab;
        private boolean good;
        private String author_id;
        private boolean top;

        public void setVisit_count(int visit_count) {
            this.visit_count = visit_count;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setAuthor(AuthorEntity author) {
            this.author = author;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setLast_reply_at(String last_reply_at) {
            this.last_reply_at = last_reply_at;
        }

        public void setReply_count(int reply_count) {
            this.reply_count = reply_count;
        }

        public void setReplies(List<RepliesEntity> replies) {
            this.replies = replies;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public void setGood(boolean good) {
            this.good = good;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public void setTop(boolean top) {
            this.top = top;
        }

        public int getVisit_count() {
            return visit_count;
        }

        public String getContent() {
            return content;
        }

        public String getId() {
            return id;
        }

        public AuthorEntity getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getLast_reply_at() {
            return last_reply_at;
        }

        public int getReply_count() {
            return reply_count;
        }

        public List<RepliesEntity> getReplies() {
            return replies;
        }

        public String getCreate_at() {
            return create_at;
        }

        public String getTab() {
            return tab;
        }

        public boolean isGood() {
            return good;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public boolean isTop() {
            return top;
        }

        public class AuthorEntity {
            /**
             * loginname : soliury
             * avatar_url : /agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F5032079%3Fv%3D3%26s%3D120
             */
            private String loginname;
            private String avatar_url;

            public void setLoginname(String loginname) {
                this.loginname = loginname;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getLoginname() {
                return loginname;
            }

            public String getAvatar_url() {
                return avatar_url;
            }
        }

        public class RepliesEntity {
            /**
             * content : <div class="markdown-text"><p>不错，顶起</p>
             </div>
             * id : 559bd4ec1e5c761761468885
             * author : {"loginname":"shinygang","avatar_url":"/agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F7077490%3Fv%3D3%26s%3D120"}
             * create_at : 2015-07-07T13:32:28.685Z
             * ups : []
             */
            private String content;
            private String id;
            private AuthorEntity author;
            private String create_at;
            private List<?> ups;

            public void setContent(String content) {
                this.content = content;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setAuthor(AuthorEntity author) {
                this.author = author;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public void setUps(List<?> ups) {
                this.ups = ups;
            }

            public String getContent() {
                return content;
            }

            public String getId() {
                return id;
            }

            public AuthorEntity getAuthor() {
                return author;
            }

            public String getCreate_at() {
                return create_at;
            }

            public List<?> getUps() {
                return ups;
            }

            public class AuthorEntity {
                /**
                 * loginname : shinygang
                 * avatar_url : /agent?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F7077490%3Fv%3D3%26s%3D120
                 */
                private String loginname;
                private String avatar_url;

                public void setLoginname(String loginname) {
                    this.loginname = loginname;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public String getLoginname() {
                    return loginname;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }
            }
        }
    }
}
