package com.demo.test.nd.httpdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;


public class MainActivity extends ActionBarActivity {

    public <T> T createApi(Class<T> cls, String endPoint) {
        return RetrofitUtils.createApi(this, cls, endPoint);
    }

    public abstract class ActivityCallback<T> implements Callback<T> {
        protected final Object mObject;

        public ActivityCallback(Object obj) {
            mObject = obj;
        }
    }

    Button mButton, mButton2;
    TextView mTextView;
    DoubanApi mDoubanApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mDoubanApi = createApi(DoubanApi.class, "https://api.douban.com/v2");

        mTextView = (TextView) this.findViewById(R.id.textView);
        mButton2 = (Button) this.findViewById(R.id.button);
        mButton = (Button) this.findViewById(R.id.button1);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button 1 click", Toast.LENGTH_SHORT).show();

                mDoubanApi.getMusic("周杰伦", 10, 0, new MusicCallback(null));

            }
        });


    }

    private final class MusicCallback extends ActivityCallback<DoubanMusic> {
        public MusicCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(DoubanMusic requestBean, retrofit.client.Response response) {
//            Toast.makeText(, requestBean.getTotal(), Toast.LENGTH_SHORT).show();
//            mButton.setText(requestBean.getTotal() + "");

//            mButton.setText("test");


            String str = "";

            for (DoubanMusic.MusicsEntity m : requestBean.getMusics()) {
                str += "专辑名：" + m.getTitle() + "\n";
                str += "作者：" + m.getAuthor().get(0).getName() + "\n";


            }

            mTextView.setText(str);

        }

        @Override
        public void failure(RetrofitError error) {
//            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//System.out.println();
        }
    }
}
