package com.ufo.smartokhttputilsproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ufo.smartokhppt.OkHttpUtils;
import com.ufo.smartokhppt.callback.DownloadCallback;
import com.ufo.smartokhppt.callback.StringCallback;
import com.ufo.smartokhppt.callback.UploadFileCallback;
import com.ufo.smartokhppt.params.RequestParams;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * sample
 */
public class MainActivity extends AppCompatActivity {

    private String url = "http://www.tngou.net/api/cook/show?id=1";
    private String url1 = "http://www.tngou.net/api/cook/show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.async_get1).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.async_get2).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.async_post1).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.async_post2).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.async_download).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.async_upload).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.async_cancel).setOnClickListener(new MyOnClickListener());
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.async_get1:
                    get1();
                    break;
                case R.id.async_get2:
                    get2();
                    break;
                case R.id.async_post1:
                    post1();
                    break;
                case R.id.async_post2:
                    post2();
                    break;
                case R.id.async_download:
                    download();
                    break;
                case R.id.async_upload:
                    upload();
                    break;
                case R.id.async_cancel:
                    cancel();
                    break;
            }
        }

    }


    private void get1() {
        OkHttpUtils.asyncGet(url, new StringCallback() {
            @Override
            public void onSuccess(Call call, String result) {
                Log.d("get1", result);
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("get1", e.toString());
            }
        });
    }

    private void get2() {
        RequestParams params = new RequestParams();
        params.put("id", "1");
        OkHttpUtils.asyncGet(url1, params, new StringCallback() {
            @Override
            public void onSuccess(Call call, String result) {
                Log.d("get2", result);
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("get2", e.toString());
            }
        });
    }


    private void post1() {
        OkHttpUtils.asyncPost(url, new StringCallback() {
            @Override
            public void onSuccess(Call call, String result) {
                Log.d("post1", result);
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("post1", e.toString());
            }
        });
    }

    private void post2() {
        RequestParams params = new RequestParams();
        params.put("id", "1");
        OkHttpUtils.asyncPost(url1, params, new StringCallback() {
            @Override
            public void onSuccess(Call call, String result) {
                Log.d("post2", result);
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("post2", e.toString());
            }
        });
    }

    private void download() {
        File file = new File("sdcard/test_download.jpg");
        String downloadUrl = "http://imgstore.cdn.sogou.com/app/a/100540002/713179.jpg";
        OkHttpUtils.asyncPost(downloadUrl, new DownloadCallback(file) {
            @Override
            public void onSuccess(Call call, File file) {
                Log.d("download", file.length() + "");
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("download failed", e.toString());
            }

            @Override
            public void onProgress(long total, int progress) {
                Log.d("download progress", progress + "");
            }
        });
    }

    private void upload() {
        File file = new File("sdcard/test_download.jpg");
        if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
        //请自已填写上传的地址
        OkHttpUtils.uploadFile("上传的rul", "image/png", file, new UploadFileCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("upload", e.toString());
            }

            @Override
            public void onSuccess(Call call, String result) {
                Log.d("upload", result);
            }

            @Override
            public void onProgress(long total, long progress) {
                Log.d("onProgress", progress + "");
            }
        });
    }

    private void cancel() {
        OkHttpUtils.cancelAllRequest();
    }

    /**
     * 添加拦截器
     */
    private void addInterceptor() {
        OkHttpUtils.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                //do some thinking

                return chain.proceed(chain.request());
            }
        });
    }





}
