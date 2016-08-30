package com.ufo.smartokhppt.params;

import android.text.TextUtils;

import com.ufo.smartokhppt.callback.UploadProgressListener;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * Created by sz on 2016/8/30.
 * <p/>
 * 描述:实现一个带有进度条的请求体,创建一个请求体一般需要文件,文件的长度,文件的类型,
 * 由于这个是带进度的请求体,所以还需要一个进度回调监听.
 */
public class UploadFileBody extends RequestBody {
    private final int size = 2048;
    private File mFile;
    private String mContentType;
    private UploadProgressListener mListener;
    private long fileLength;

    public UploadFileBody(File file, String contentType, UploadProgressListener listener) {
        this.mFile = file;
        this.mContentType = contentType;
        this.mListener = listener;
        if (null == mFile || mFile.length() <= 0) {
            throw new IllegalArgumentException("Upload File can not empty");
        }
        if (TextUtils.isEmpty(mContentType)) {
            throw new IllegalArgumentException("Upload File contentType can not null");
        }

        fileLength = mFile.length();
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse(mContentType);
    }

    @Override
    public long contentLength() throws IOException {
        return fileLength;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Source source = null;
        try {
            source = Okio.source(mFile);
            long total = 0;
            long read;
            while ((read = source.read(sink.buffer(), size)) != -1) {
                total += read;
                sink.flush();
                this.mListener.progress(fileLength, total);
            }
        } finally {
            Util.closeQuietly(source);
        }
    }


}
