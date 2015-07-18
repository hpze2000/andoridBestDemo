package com.demo.nd.test.fragment.demo;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.nd.test.R;
import com.demo.nd.test.base.BaseFragment;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by johnson on 2015/7/18.
 */
public class GifFragment extends BaseFragment implements View.OnClickListener {

    Button mButton1, mButton2, mButton3, mButton4;
    GifImageView mGifImageView;
    OkHttpClient client;
    File downGifFile;
    MyHandler mHandler = new MyHandler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_gif, null);

        mGifImageView = (GifImageView) view.findViewById(R.id.giv_gif);
        mButton1 = (Button) view.findViewById(R.id.btn_gif_1);
        mButton2 = (Button) view.findViewById(R.id.btn_gif_2);
        mButton3 = (Button) view.findViewById(R.id.btn_gif_3);
        mButton4 = (Button) view.findViewById(R.id.btn_gif_4);

        client = new OkHttpClient();

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        downGif("http://img3.cache.netease.com/sports/2015/7/16/20150716161934af0b3.gif", "gif_1.gif");



        return view;
    }

    void downGif(String url, String filename) {
        final String fname = filename;

        File fileH = new File(Environment.getExternalStorageDirectory() + "/" + fname);
        if (fileH.exists()) {
            downGifFile = new File(Environment.getExternalStorageDirectory() + "/" + fname);

            mHandler.sendEmptyMessageDelayed(1, 0);
            return;
        }

        Request request = new Request.Builder()
                .url(url) //"http://img3.cache.netease.com/sports/2015/7/16/20150716161934af0b3.gif"
                .build();


        showWaitDialog().show();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                hideWaitDialog();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                downGifFile = new File(Environment.getExternalStorageDirectory() + "/" + fname);
                inputstreamtofile(response.body().byteStream(), downGifFile);

                mHandler.sendEmptyMessageDelayed(1, 0);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mGifImageView != null) {
            mGifImageView.destroyDrawingCache();
            ((GifDrawable) mGifImageView.getBackground()).recycle();
            mGifImageView = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gif_1:
                downGif("http://img3.cache.netease.com/sports/2015/7/16/20150716161934af0b3.gif", "gif_1.gif");
                break;
            case R.id.btn_gif_2:
                downGif("http://s1.dwstatic.com/group1/M00/69/D7/c1c9fbe0c54cb3156de41faf8c1cbafc.gif", "gif_2.gif");
                break;
            case R.id.btn_gif_3:
                downGif("http://s1.dwstatic.com/group1/M00/BC/23/72e33a688ed6af725ad423a7dfbdc76b.gif", "gif_3.gif");
                break;
            case R.id.btn_gif_4:
                downGif("http://s1.dwstatic.com/group1/M00/0E/E3/00cc2beaed636f0308ed8ba38cb30e95.gif", "gif_4.gif");
                break;
        }
    }


    public void inputstreamtofile(InputStream ins, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    GifDrawable gifFromFile = null;
                    try {
                        if (gifFromFile != null)
                            gifFromFile.reset();

                        gifFromFile = new GifDrawable( downGifFile );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mGifImageView.setBackground(gifFromFile);

                    gifFromFile.start();

                    hideWaitDialog();
                    break;
            }
        }
    };


}
