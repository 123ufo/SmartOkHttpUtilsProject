package com.ufo.smartokhppt.callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/20.
 */
public abstract class ResponseCallback extends BaseResultCallback {

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(call, response);
            }
        });
    }

    /**
     * 执行在UI线程成功回调
     *
     * @param call
     * @param response
     */
    public abstract void onSuccess(Call call, Response response);

}
