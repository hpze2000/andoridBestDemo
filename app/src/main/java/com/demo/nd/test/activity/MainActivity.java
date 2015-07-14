package com.demo.nd.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

        final String fileName = "demo.jpg";
        btn_demo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = new File(Environment.getExternalStorageDirectory() + "/" + fileName);

                if (file.exists()) {
                    String mimeType = "";
                    try {
                        mimeType = getMimeType("file://" + Environment.getExternalStorageDirectory() + "/" + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    showWaitDialog().show();
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
                        .url("http://www.sinaimg.cn/dy/slidenews/48_img/2015_03/33487_3983732_283301.jpg")
                        .build();

                OkHttpUtils.getInstance(MainActivity.this).newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        byte[] bytes = response.body().bytes();

                        try {
                            saveBytesToFile(bytes, Environment.getExternalStorageDirectory() + "/" + fileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Looper.prepare();
                        Toast.makeText(MainActivity.this, "文件下载完成，保存位置 ： " + Environment.getExternalStorageDirectory() + "/" + fileName, Toast.LENGTH_LONG).show();
                        hideWaitDialog();
                        Looper.loop();
                    }
                });
            }
        });
    }


    public String getMimeType(String fileUrl)
            throws java.io.IOException
    {
        String type = null;
        URL u = new URL(fileUrl);
        URLConnection uc = null;
        uc = u.openConnection();
        type = uc.getContentType();
        return type;
    }

    public byte[] getBytesFromStream(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        buf = new byte[size];
        while((len = is.read(buf, 0, size)) != -1) {
            bos.write(buf, 0, len);
        }
        buf = bos.toByteArray();

        return buf;
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
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            hideWaitDialog();
        }
    }

}
