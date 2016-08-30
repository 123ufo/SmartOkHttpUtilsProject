package com.ufo.smartokhppt.callback;

/**
 * Created by sz on 2016/8/30.
 * 描述:文件上传进度监听
 */
public interface UploadProgressListener {
    /**
     * @param total    总的大小
     * @param progress 当前已上传的大小
     */
    void progress(long total, long progress);
}
