package com.ufo.smartokhppt.callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/21.
 */
public abstract class ByteArrayCallback extends BaseResultCallback {


    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final byte[] result = response.body().bytes();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(call, result);
            }
        });
    }

    public abstract void onSuccess(Call call, byte[] result);

}
