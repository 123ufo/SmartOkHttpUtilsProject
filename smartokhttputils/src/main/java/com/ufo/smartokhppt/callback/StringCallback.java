package com.ufo.smartokhppt.callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/21.
 * 描述:字符串回调接口 运行在UI线程上的
 */
public abstract class StringCallback extends BaseResultCallback {

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

    public abstract void onSuccess(Call call, String result);

    @Override
    public abstract void onFailed(Call call, IOException e);
}
