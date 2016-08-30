package com.ufo.smartokhppt.callback;

import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/21.
 */
public abstract class DownloadCallback extends BaseResultCallback {

    /**
     * 进度的message
     */
    private static final int PROGRESS_MESSAGE = 1;
    /**
     * 默认缓冲区大小
     */
    private static final int DEFAULT_BUF_SIZE = 4096;
    private final File file;


    public DownloadCallback(File file) {
        if (null == file) {
            throw new IllegalArgumentException("file can not null");
        }
        this.file = file;
    }

    @Override
    public void onResponse(final Call call, Response response) {
        long length = response.body().contentLength();
        InputStream is = response.body().byteStream();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            int progressLength = 0;
            byte[] buf = new byte[DEFAULT_BUF_SIZE];
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                fos.flush();
                progressLength += len;
                sendMessage(length, progressLength);
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onSuccess(call,file);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 发送进度消息
     * @param total
     * @param progress
     */
    private void sendMessage(long total, int progress) {
        Message obtain = Message.obtain();
        obtain.what = PROGRESS_MESSAGE;
        obtain.obj = new Object[]{total, progress};
        mHandler.sendMessage(obtain);
    }

    public void onProgress(long total, int progress) {

    }

    public abstract void onSuccess(Call call, File file);

    @Override
    protected void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what == PROGRESS_MESSAGE) {
            Object[] obj = (Object[]) msg.obj;
            onProgress((long) obj[0], (Integer) obj[1]);
        }
    }
}
