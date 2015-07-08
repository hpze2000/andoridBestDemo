package com.demo.nd.test.activity;

import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.demo.nd.test.R;
import com.demo.nd.test.api.ExpertApi;
import com.demo.nd.test.bean.Bean;
import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import retrofit.RetrofitError;
import retrofit.client.Response;


@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends BaseActivity  {
    public final static String TAG = "MainActivity";

    private ExpertApi mExpertApi;

    @ViewById(R.id.fragment_home_menu_list_view)
    ListView mListView;
    @ViewById(R.id.fragment_home_ptr_frame)
    PtrFrameLayout ptrFrame;

    @OptionsMenuItem(R.id.action_settings)
    MenuItem menuSettings;

    private Handler handler = new Handler();
    private MaterialDialog progressDialog;

    @AfterViews
    void init(){
        mExpertApi = createApi(ExpertApi.class);

        progressDialog = new MaterialDialog.Builder(this)
                .cancelable(false)
                .theme(Theme.LIGHT)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();


        initPtrFrame();
    }

    /**
     * 初始化 下拉控件
     */
    void initPtrFrame() {
        final StoreHouseHeader header = new StoreHouseHeader(MainActivity.this);
        header.initWithStringArray(R.array.storehouse);
        ptrFrame.setDurationToCloseHeader(1500);
        ptrFrame.setHeaderView(header);
        ptrFrame.addPtrUIHandler(header);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                getData();
            }
        });

        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrame.autoRefresh();
            }
        }, 100);
    }


    @Override
    protected void onDestroy() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.getWindow().getAttributes().windowAnimations = android.R.anim.fade_out;
            progressDialog.cancel();
        }

        super.onDestroy();
    }

    @OptionsItem(R.id.action_settings)
    void setting() {

    }

    void getData() {
        showProgressDialog();

        mExpertApi.showAjaxData("", 1, "", new AjaxDataCallback(MainActivity.this));
    }

    private final class AjaxDataCallback extends ActivityCallback<Bean> {
        public AjaxDataCallback(MainActivity activity) {
            super(activity);
        }

        @Override
        public void success(Bean requestBean, Response response) {
            if (requestBean.getStatus() == 200) {
                List<HashMap<String, String>> data = new ArrayList();
                for (String s : requestBean.getData().getLegend()) {
                    HashMap<String, String> map = new HashMap();

                    map.put("title", s);
                    map.put("description", s);

                    data.add(map);
                }

                SimpleAdapter adapter =  new SimpleAdapter(MainActivity.this,
                        data,
                        android.R.layout.two_line_list_item,
                        new String[]{"title", "description"},
                        new int[]{android.R.id.text1, android.R.id.text2}
                );

                mListView.setAdapter(adapter);
            } else {
                Logger.e(response.getReason());
            }

            hideProgressDialog();

            ptrFrame.refreshComplete();
        }

        @Override
        public void failure(RetrofitError error) {
            super.failure(error);

            hideProgressDialog();
        }
    }

    @UiThread
    public void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.getWindow().getAttributes().windowAnimations =
                            android.R.anim.fade_out;
                    progressDialog.dismiss();
                }
            }, 300);
        }
    }

    @UiThread
    public void showProgressDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.getWindow().getAttributes().windowAnimations = android.R.anim.fade_in;
            progressDialog.show();
        }
    }

}
