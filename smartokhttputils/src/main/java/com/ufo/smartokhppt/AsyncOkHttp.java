package com.ufo.smartokhppt;

import com.ufo.smartokhppt.callback.UploadFileCallback;
import com.ufo.smartokhppt.params.RequestParams;

import java.io.File;

import okhttp3.Callback;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/5/17.
 * <p/>
 * 异步okhttp
 */
class AsyncOkHttp extends BaseHttp {

    private static final String TAG = "AsyncOkHttp";


    /**
     * 异步get请求
     *
     * @param url      请求地址，不能为空
     * @param callback 回调接口，不能为空
     */
    public void asyncGet(String url, Callback callback) {
        asyncGet(url, null, callback);
    }

    /**
     * 异步get请求
     *
     * @param url      请求地址，不能为空
     * @param params   请求参数，可以为空
     * @param callback 回调接口，不能为空
     */
    public void asyncGet(String url, RequestParams params, Callback callback) {
        Request request = createRequest(url, params, Method.GET);
        asyncRequest(request, callback);
    }

    /**
     * 异步post请求
     *
     * @param url      请求地址
     * @param callback 回调接口
     */
    public void asyncPost(String url, Callback callback) {
        asyncPost(url, null, callback);
    }

    /**
     * 异步post请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param callback 回调接口
     */
    public void asyncPost(String url, RequestParams params, Callback callback) {
        Request request = createRequest(url, params, Method.POST);
        asyncRequest(request, callback);
    }

    /**
     * 上传文件
     *
     * @param url         请求地下
     * @param contentType 需要上传的文件的类型 如:图片"image/jpeg"; "image/png".
     * @param file        要上传的文件
     * @param callback    回调接口
     */
    public void uploadFile(String url, String contentType, File file, UploadFileCallback callback) {
        Request request = createUploadRequest(url, contentType, file, callback);
        asyncUploadRequest(request, callback);
    }


}
