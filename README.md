# 自己动手开发一个安卓APP - HTTP 网络请求 

标签（空格分隔）： HTTP Android 

----------
## 总共4个课程 包含以下内容
> *  第一节 ：android开发环境设置，android HTTP 请求，与服务端交互（图片请求，JSON 解析 缓存处理等）
> *  第二节 ：android 常用组件/控件使用介绍（activity，fragment，actionBar，dialog，tabhost，listview，RecyclerView，view切换，侧滑菜单，下拉刷新组件 等
> *  第三节 ：Service，Broadcast，存储及数据操作，多线程及异步任务，动画等
> *  第四节 ：项目打包，签名，混淆，第三方组件的使用（广告平台，IM SDK，消息推送，自动更新，统计分析，渠道发布，日志监控，用户反馈，社会化分享，定位LBS，支付等）

## 编写目的
> 主要是为了分享 如何快速的进行一门语言或者一门技术的学习，采用非循序渐进的方法，让被分享者中那些对这么技术或语言感兴趣的同学，能够迅速的进入开发状态或者迅速的开发出成品。
> 针对的人群，及技术要求，没有限制，主要是 有兴趣和愿意动手
> 主要是开发学习过程中学习经验和方法的分享，授人以渔。

## 课程源码
[源码 github 项目地址（有github账号的记得帮忙加星哦）][1]
[源码 Git.oschina.net 项目地址][2]
![主界面][3]
![列表页面][4]
----------

##以下为第一节内容

----------

### 1. 开始 （安卓开发环境） 
    很多时间开发环境的配置会导致很多想学习一门语言或者一门技术的人失去信心和失去兴趣，所以开发环境的介绍还是很有必要。
 - [使用 android studio 进行安卓开发][5]
 - [使用安卓模拟器 Genymotion 进行开发调试][6]

----------

### 2. HTTP 协议概要

----------

:   ####什么是HTTP协议

        协议是指计算机通信网络中两台计算机之间进行通信所必须共同遵守的规定或规则，超文本传输协议(HTTP)是一种通信协议，它允许将超文本标记语言(HTML)文档从Web服务器传送到客户端的浏览器，目前我们使用的是HTTP/1.1 版本
        
:   #### Get和Post方法的区别

    > * Http协议定义了很多与服务器交互的方法，最基本的有4种，分别是GET,POST,PUT,DELETE. 一个URL地址用于描述一个网络上的资源，而HTTP中的GET, POST,PUT,DELETE就对应着对这个资源的查，改，增，删4个操作。我们最常见的就是GET和POST了。GET一般用于获取/查询资源信息，而POST一般用于更新资源信息.
    > * GET提交的数据会放在URL之后，以?分割URL和传输数据，参数之间以&相连，如EditPosts.aspx?name=test1&id=123456.  POST方法是把提交的数据放在HTTP包的Body中.
    > * GET提交的数据大小有限制（因为浏览器对URL的长度有限制），而POST方法提交的数据没有限制.

:   #### 状态码

    > * 1XX  提示信息 - 表示请求已被成功接收，继续处理
    > * 2XX  成功 - 表示请求已被成功接收，理解，接受
        - 200 服务器成功处理了请求
    > * 3XX  重定向 - 要完成请求必须进行更进一步的处理
        - 301 请求的网页已永久移动到新的位置，当服务器返回此响应(作为一个GET或HEAD请求的响应)，它会自动转发请求到新的位置
        - 304 Not Modified 代表上次的文档已经被缓存了， 还可以继续使用
    > * 4XX  客户端错误 -  请求有语法错误或请求无法实现
        - 400 Bad Request  客户端请求与语法错误，不能被服务器所理解
        - 403 Forbidden 服务器收到请求，但是拒绝提供服务
        - 404 Not Found 请求资源不存在（输错了URL）
    > * 5XX  服务器端错误 -   服务器未能实现合法的请求
        - 500 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。一般来说，这个问题都会在服务器的程序码出错时出现。
        - 502 504 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。
        - 503 Server Unavailable 服务器当前不能处理客户端的请求，一段时间后可能恢复正常

:   #### COOKIE & SESSION

    > * Cookie是什么？
        - Cookie 是一小段文本信息，伴随着用户请求和页面在 Web 服务器和浏览器之间传递。Cookie 包含每次用户访问站点时 Web 应用程序都可以读取的信息。
    > * 为什么需要Cookie？
        - 因为HTTP协议是无状态的，对于一个浏览器发出的多次请求，WEB服务器无法区分 是不是来源于同一个浏览器。所以，需要额外的数据用于维护会话。 Cookie 正是这样的一段随HTTP请求一起被传递的额外数据。
    > * Cookie能做什么？
        - Cookie只是一段文本，所以它只能保存字符串。而且浏览器对它有大小限制以及 它会随着每次请求被发送到服务器，所以应该保证它不要太大。 Cookie的内容也是明文保存的，有些浏览器提供界面修改，所以， 不适合保存重要的或者涉及隐私的内容。
        
:   参考链接： [HTTP协议详解系列文档（一个测试同行的博客链接，内容值得一看）][7]


----------


### 3. 使用 Android APP 作为客户端 发送一个 http get 请求

----------

:   #### 1. 看看如果使用安卓原生代码发送一个 http get 请求
    ```java
    public function httpGet(String url) {
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(url);
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			String result = readInStream(in);
			handleResult(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			urlConnection.disconnect();
		}
    }
    private void handleResult(String result) {
		System.out.println(result);
		// TODO 这里完成 对JSON,XML 等数据的 处理 界面的交互等操作
	}
	//在按钮点击事件中 这样调用
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnRequest = (Button) findViewById(R.id.btn_request);
		btnRequest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						httpGet("http://www.baidu.com");
					}
				}).start();
			}
		});
	}
    /**
     * 以上代码存在的诸多问题
     * 1： 需要优化异步操作
     * 2： 网络请求的排序（scheduling）
     * 3： 网络请求的优先级处理
     * 4： 缓存
     * 5： 多级别取消请求
     * 6： 和Activity和生命周期的联动（Activity结束时同时取消所有网络请求） 等
     */
    ```
    #### 2. 站在巨人的肩膀 使用 推荐的库完成 高效/合理的 HTTP 请求 此处只列出这两个，类似库上还有很多
    -  [volley (Android 官方)][8] 
    -  [OKHTTP (很棒HTTP客户端 与Retrofit配合使用)][9] 
    -  [retrofit (Retrofit把RESTAPI返回的数据转化为Java对象方便操作。同时还封装了网络代码的调用)][10] 
    - 因为本人主要是使用过okhttp，所以此处直接介绍这个库的使用为例，主要还是直接查看库的文档 （设置 3 个类文件）
            
    ```java
        /**
         * OkHttpUtils.java
         * 单例一个 OkHttpClient  
         */
        public class OkHttpUtils {
            private static OkHttpClient singleton;
        
            public static OkHttpClient getInstance(Context context) {
                if (singleton == null) {
                    synchronized (OkHttpUtils.class) {
                        if (singleton == null) {
                            //缓存保存的目录信息
                            File cacheDir = new File(context.getCacheDir(), Config.RESPONSE_CACHE);
                            singleton = new OkHttpClient();
                            //设置缓存
                            singleton.setCache(new Cache(cacheDir, Config.RESPONSE_CACHE_SIZE));
                            //设置连接超时
                            singleton.setConnectTimeout(Config.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                            //设置读取超时
                            singleton.setReadTimeout(Config.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
                            //设置 cookie 
                            CookieManager cookieManager = new CookieManager();
                            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                            singleton.setCookieHandler(cookieManager);
                        }
                    }
                }
                return singleton;
            }
        }
        
        /**
         * GsonUtils.java
         * 单例一个 Gson 这里我们使用 GSON 库 左右JSON的解析库
         */
        public class GsonUtils {
            public static Gson newInstance() {
                GsonBuilder builder = new GsonBuilder();
                return builder.create();
            }
        }
        
        /**
         * RetrofitUtils.java
         * 单例一个 Retrofit
         */
        public class RetrofitUtils {
            //因为这里考虑到 项目需要访问多个 不同 endPoint 也就是域名的需求，所有启用hashmap左右存储单例对象
            private static HashMap<String, RestAdapter> singletons = new HashMap();
        
            public static <T> T createApi(Context context, Class<T> clazz, String endPoint) {
                if (singletons.get(endPoint) == null) {
                    synchronized (RetrofitUtils.class) {
                        if (singletons.get(endPoint) == null) {
                            RestAdapter.Builder builder = new RestAdapter.Builder();
                            //设置 调用API 的基础域名 比如：http://api.meitu.tv
                            builder.setEndpoint(endPoint);
                            //设置 返回数据的转换对象 也就是上面的 GsonUtils
                            builder.setConverter(new GsonConverter(GsonUtils.newInstance()));
                            //设置 http 请求的客户端 也就是 上面的 OkHttpUtils
                            builder.setClient(new OkClient(OkHttpUtils.getInstance(context)));
                            //设置要显示的日志等级
                            builder.setLogLevel(
                                    Config.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);
                            //这里可以设置 每个请求时要注入的的信息，比如此处设置了 加入头信息的 缓存参数
                            builder.setRequestInterceptor(new RequestInterceptor() {
                                @Override
                                public void intercept(RequestFacade request) {
                                    if (DeviceUtils.hasInternet()) {
                                        int maxAge = 60; // read from cache for 1 minute
                                        request.addHeader("Cache-Control", "public, max-age=" + maxAge);
                                    } else {
                                        int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                                        request.addHeader("Cache-Control",
                                                "public, only-if-cached, max-stale=" + maxStale);
                                    }
                                }
                            });
                            singletons.put(endPoint, builder.build());
                        }
                    }
                }
                return singletons.get(endPoint).create(clazz);
            }
        }
    ```
        


  [1]: https://github.com/hpze2000/andoridBestDemo
  [2]: http://git.oschina.net/hpze2000/andoridBestDemo
  [3]: http://images.meitu.tv/demo_1.jpg
  [4]: http://images.meitu.tv/demo_2.jpg
  [5]: http://www.android-studio.org/
  [6]: https://www.genymotion.com/
  [7]: http://www.cnblogs.com/TankXiao/archive/2012/02/13/2342672.html
  [8]: https://android.googlesource.com/platform/frameworks/volley
  [9]: http://square.github.io/okhttp/
  [10]: http://square.github.io/retrofit/