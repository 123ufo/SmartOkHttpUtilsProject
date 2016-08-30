package com.ufo.smartokhppt.callback;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by sz on 2016/8/30.
 * 描述:文件上传回调接口.
 */
public abstract class UploadFileCallback extends BaseResultCallback implements UploadProgressListener {
    protected static Handler mHandler;

    public UploadFileCallback() {
        mHandler = new Handler(Looper.getMainLooper());
    }


    @Override
    public abstract void onFailed(Call call, IOException e);

    public abstract void onSuccess(Call call, String result);

    public abstract void onProgress(long total, long progress);

    @Override
    public void progress(final long total, final long progress) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onProgress(total, progress);
            }
        });
    }

    @Override
    public void onResponse(final Call call, Response response) throws IOException {
        final String result = response.body().string();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(call, result);
            }
        });
    }
}
