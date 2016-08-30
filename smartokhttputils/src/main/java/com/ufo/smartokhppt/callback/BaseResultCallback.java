package com.ufo.smartokhppt.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * Created by Administrator on 2016/5/20.
 */
public abstract class BaseResultCallback implements Callback {

    protected static Handler mHandler;

    public BaseResultCallback() {
        mHandler = new CallbackHandler(this, Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(call, e);
            }
        });
    }

    public abstract void onFailed(Call call, IOException e);

    protected void handleMessage(Message msg) {
    }

    class CallbackHandler extends Handler {
        BaseResultCallback mBaseResultCallback = null;

        public CallbackHandler(BaseResultCallback baseResultCallback, Looper looper) {
            super(looper);
            this.mBaseResultCallback = baseResultCallback;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mBaseResultCallback.handleMessage(msg);
        }
    }


}
