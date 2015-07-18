package com.demo.nd.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
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
import java.net.URL;
import java.net.URLConnection;

import retrofit.RetrofitError;
import retrofit.mime.TypedFile;
import ru.bartwell.exfilepicker.ExFilePickerParcelObject;


public class MainActivity extends BaseMintsActivity {

    Button btnCnodejsList, btnMeizituList, btn_demo_meizitu_list_2, btn_demo_login, btn_demo_download, btn_demo_upload, btn_demo_vedio, btn_demo_gif;
    MeizituApi mMeizituApi;
    private static final int EX_FILE_PICKER_RESULT = 0;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        mMeizituApi = createApi(MeizituApi.class, "http://api.meitu.tv");
        btnCnodejsList = (Button) findViewById(R.id.btn_demo_list);
        btnMeizituList = (Button) findViewById(R.id.btn_demo_meizitu_list);
        btn_demo_meizitu_list_2 = (Button) findViewById(R.id.btn_demo_meizitu_list_2);
        btn_demo_login = (Button) findViewById(R.id.btn_demo_login);
        btn_demo_download = (Button) findViewById(R.id.btn_demo_download);
        btn_demo_upload = (Button) findViewById(R.id.btn_demo_upload);
        btn_demo_vedio = (Button) findViewById(R.id.btn_demo_vedio);
        btn_demo_gif = (Button) findViewById(R.id.btn_demo_gif);

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
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.meizitu.MeizituListBGAFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "meitu.tv API");
                startActivity(intent);
            }
        });

        btn_demo_meizitu_list_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleBackActivity.class);
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.meizitu.MeizituListFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "meitu.tv API 2");
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
                Intent intent = new Intent(getApplicationContext(), ru.bartwell.exfilepicker.ExFilePickerActivity.class);
                intent.putExtra("EnableCancelButton", true);
                startActivityForResult(intent, EX_FILE_PICKER_RESULT);
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

        btn_demo_vedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleBackActivity.class);
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.demo.VideoFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "视频播放");
                startActivity(intent);
            }
        });

        btn_demo_gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleBackActivity.class);
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.demo.GifFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "GIF动图播放");
                startActivity(intent);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EX_FILE_PICKER_RESULT) {
            if (data != null) {
                ExFilePickerParcelObject object = data.getParcelableExtra(ExFilePickerParcelObject.class.getCanonicalName());
                if (object.count > 0) {
//                    Toast.makeText(MainActivity.this, object.path + object.names.get(0), Toast.LENGTH_LONG).show();

                    String selectFileName = object.path + object.names.get(0);
                    File file = new File(selectFileName);
                    if (file.exists()) {
                        if (file.length() > 1024 * 1024 * 10) {
                            Toast.makeText(MainActivity.this, "文件大小过大：" + file.length(), Toast.LENGTH_LONG).show();
                            return;
                        }

                        String mimeType = "";
                        try {
                            mimeType = getMimeType("file://" + selectFileName);
                        } catch (IOException e) {
//                            e.printStackTrace();
                        }

                        if (TextUtils.isEmpty(mimeType)) {
                            Toast.makeText(MainActivity.this, "类型读取错误", Toast.LENGTH_LONG).show();
                            return;
                        }
                        TypedFile fileToSend = new TypedFile(mimeType, file);
                        showWaitDialog().show();

                        mMeizituApi.upload(fileToSend, "Default.Upload", new UploadDataCallback(null));
                    } else {
                        Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
