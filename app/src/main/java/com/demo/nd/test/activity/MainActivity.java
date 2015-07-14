package com.demo.nd.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.nd.test.R;
import com.demo.nd.test.api.MeizituApi;
import com.demo.nd.test.base.BaseMintsActivity;
import com.demo.nd.test.bean.MeizituBaseBean;
import com.demo.nd.test.utils.OkHttpUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit.RetrofitError;
import retrofit.mime.TypedFile;


public class MainActivity extends BaseMintsActivity {

    Button btnCnodejsList, btnMeizituList, btn_demo_login, btn_demo_download, btn_demo_upload;
    MeizituApi mMeizituApi;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        mMeizituApi = createApi(MeizituApi.class, "http://api.meitu.tv");
        btnCnodejsList = (Button) findViewById(R.id.btn_demo_list);
        btnMeizituList = (Button) findViewById(R.id.btn_demo_meizitu_list);
        btn_demo_login = (Button) findViewById(R.id.btn_demo_login);
        btn_demo_download = (Button) findViewById(R.id.btn_demo_download);
        btn_demo_upload = (Button) findViewById(R.id.btn_demo_upload);

        btnCnodejsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleBackActivity.class);
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.cnodejs.CnodejsListFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "cnodejs.org API");
                startActivity(intent);
            }
        });

        btnMeizituList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleBackActivity.class);
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.meizitu.MeizituListFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "meitu.tv API");
                startActivity(intent);
            }
        });

        btn_demo_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleBackActivity.class);
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.demo.LoginFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "登录演示");
                startActivity(intent);
            }
        });

        final String fileName = "demo.gif";
        btn_demo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = new File(Environment.getExternalStorageDirectory() + "/" + fileName);

                if (file.exists()) {
                    showWaitDialog().show();
                    String mimeType = "image/gif";
                    TypedFile fileToSend = new TypedFile(mimeType, file);

                    mMeizituApi.upload(fileToSend, "Default.Upload", new UploadDataCallback(null));
                } else {
                    Toast.makeText(MainActivity.this, "没有对应文件， 请先点击下载测试", Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_demo_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaitDialog().show();
                Request request = new Request.Builder()
                        .url("http://flash2-http.qq.com/bz.gif")
                        .build();

                OkHttpUtils.getInstance(MainActivity.this).newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        byte[] bytes = response.body().bytes();

                        saveBytesToFile(bytes, Environment.getExternalStorageDirectory() + "/" + fileName);

                        Toast.makeText(MainActivity.this, "文件位置 ： " + Environment.getExternalStorageDirectory() + "/" + fileName, Toast.LENGTH_LONG).show();

                        hideWaitDialog();
                    }
                });
            }
        });




    }

    public void saveBytesToFile(byte[] bytes, String path) throws IOException {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }

        FileOutputStream fileOuputStream = null;
        try {
            fileOuputStream = new FileOutputStream(path);
            fileOuputStream.write(bytes);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally{
            if (fileOuputStream != null) {
                fileOuputStream.close();
            }
        }
    }


    private final class UploadDataCallback extends ActivityCallback<MeizituBaseBean> {
        public UploadDataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(MeizituBaseBean requestBean, retrofit.client.Response response) {
            if (requestBean.getRet() == 200) {
                Toast.makeText(MainActivity.this, requestBean.getData(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, requestBean.getData(), Toast.LENGTH_LONG).show();
            }

            hideWaitDialog();
        }

        @Override
        public void failure(RetrofitError error) {

            hideWaitDialog();
        }
    }


//    public final static String TAG = "MainActivity";
//    int currentPage = 1;
//
//    CnodejsApi mCnodejsApi;
//    CnodejsListAdapter mMainListAdapter;
//
//    ListView mListView;
//    PtrFrameLayout ptrFrame;
//    LoadMoreListViewContainer loadMoreListViewContainer;
//
//
//    private Handler handler = new Handler();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//
//        initUi();
//        init();
//    }
//
//    void initUi() {
//        setContentView(R.layout.activity_main);
//
//        mListView = (ListView) findViewById(R.id.fragment_home_menu_list_view);
//        ptrFrame = (PtrFrameLayout) findViewById(R.id.fragment_home_ptr_frame);
//    }
//
//    void init() {
//        mCnodejsApi = createApi(CnodejsApi.class, "https://cnodejs.org");
//        mMainListAdapter = new CnodejsListAdapter();
//
//        initPtrFrame();
//    }
//
//    /**
//     * 初始化 下拉控件
//     */
//    void initPtrFrame() {
//        ptrFrame.setLoadingMinTime(1000);
//        ptrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                getData(true);
//            }
//        });
//
//
//        loadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
//        loadMoreListViewContainer.useDefaultFooter();
//
//        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
//            @Override
//            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
//                getData(false);
//            }
//        });
//        loadMoreListViewContainer.loadMoreFinish(false, true);
//
//
//        ptrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ptrFrame.autoRefresh();
//            }
//        }, 100);
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//
//    void getData(boolean isFirstPage) {
//        if (isFirstPage) {
//            currentPage = 1;
//        }
//
//        mCnodejsApi.topics(currentPage, 20, "", true, new AjaxDataCallback(this, isFirstPage));
//
//        currentPage++;
//    }
//
//    @Override
//    protected String getCloseWarning() {
//        return null;
//    }
//
//    @Override
//    protected int getFragmentContainerId() {
//        return 0;
//    }
//
//    @Override
//    public void addComponent(LifeCycleComponent component) {
//
//    }
//
//    private final class AjaxDataCallback extends ActivityCallback<CnodejsTopicsBean> {
//        public AjaxDataCallback(MainActivity activity, Object obj) {
//            super(activity, obj);
//        }
//
//        @Override
//        public void success(CnodejsTopicsBean requestBean, Response response) {
//            boolean isFirstPage = (boolean) mobj;
//
//            if (isFirstPage) {
//                mMainListAdapter.clear();
//                mMainListAdapter.addData(requestBean.getData());
//                mListView.setAdapter(mMainListAdapter);
//
//                ptrFrame.refreshComplete();
//            } else {
//                mMainListAdapter.addData(requestBean.getData());
//                mMainListAdapter.notifyDataSetChanged();
//                loadMoreListViewContainer.loadMoreFinish(mMainListAdapter.isEmpty(), !mMainListAdapter.isEmpty());
//            }
//        }
//
//        @Override
//        public void failure(RetrofitError error) {
//            super.failure(error);
//
//            ptrFrame.refreshComplete();
//        }
//    }


}
