package com.ufo.smartokhppt;

import com.ufo.smartokhppt.callback.UploadFileCallback;
import com.ufo.smartokhppt.loger.LogUtil;
import com.ufo.smartokhppt.params.RequestParams;

import java.io.File;
import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/17.
 * <p/>
 * okhttp工具类,入口类
 */
public class OkHttpUtils {

    private static AsyncOkHttp mAsyncOkHttp = new AsyncOkHttp();
    private static SyncOkHttp mSyncOkHttp = new SyncOkHttp();

    public static void setDebug(boolean debug) {
        LogUtil.setIsDebug(debug);
    }

    /**
     * 添加一个拦截器
     *
     * @param interceptor
     */
    public static void addInterceptor(Interceptor interceptor) {
        AsyncOkHttp.addInterceptor(interceptor);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAllRequest() {
        BaseHttp.cancelAllRequest();
    }

    /**
     * 异步get请求
     *
     * @param url      请求地址，不能为空
     * @param callback 回调接口，不能为空
     */
    public static void asyncGet(String url, Callback callback) {
        mAsyncOkHttp.asyncGet(url, callback);
    }

    /**
     * 异步get请求
     *
     * @param url      请求地址，不能为空
     * @param params   请求参数，可以为空
     * @param callback 回调接口，不能为空
     */
    public static void asyncGet(String url, RequestParams params, Callback callback) {
        mAsyncOkHttp.asyncGet(url, params, callback);
    }

    /**
     * 异步post请求
     *
     * @param url      请求地址
     * @param callback 回调接口
     */
    public static void asyncPost(String url, Callback callback) {
        mAsyncOkHttp.asyncPost(url, callback);

    }

    /**
     * 异步post请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param callback 回调接口
     */
    public static void asyncPost(String url, RequestParams params, Callback callback) {
        mAsyncOkHttp.asyncPost(url, params, callback);
    }

    /**
     * 上传文件
     *
     * @param url         请求地下
     * @param contentType 需要上传的文件的类型 如:图片"image/jpeg"; "image/png".
     * @param file        要上传的文件
     * @param callback    回调接口
     */
    public static void uploadFile(String url, String contentType, File file, UploadFileCallback callback) {
        mAsyncOkHttp.uploadFile(url, contentType, file, callback);
    }

    /**
     * ------------------------------同步-----------------------------------------
     */

    /**
     * 同步 http get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回Response形式的结果
     * @throws IOException
     */
    public static Response syncGetResponse(String url, RequestParams params) throws IOException {
        return mSyncOkHttp.syncGetResponse(url, params);
    }

    /**
     * 同步 http get请求
     *
     * @param url 请求地址
     * @return 返回Response形式的结果
     * @throws IOException
     */
    public static Response syncGetResponse(String url) throws IOException {
        return mSyncOkHttp.syncGetResponse(url);
    }

    /**
     * 同步 http get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回byte[]形式的结果
     * @throws IOException
     */
    public static byte[] syncGetByteArray(String url, RequestParams params) throws IOException {
        return mSyncOkHttp.syncGetByteArray(url, params);
    }

    /**
     * 同步 http get请求
     *
     * @param url 请求地址
     * @return 返回byte[]形式的结果
     * @throws IOException
     */
    public static byte[] syncGetByteArray(String url) throws IOException {
        return mSyncOkHttp.syncGetByteArray(url);
    }

    /**
     * 同步 http get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回string形式的结果
     * @throws IOException
     */
    public static String syncGetString(String url, RequestParams params) throws IOException {
        return mSyncOkHttp.syncGetString(url, params);
    }

    /**
     * 同步 http get请求
     *
     * @param url 请求地址
     * @return 返回string形式的结果
     * @throws IOException
     */
    public static String syncGetString(String url) throws IOException {
        return mSyncOkHttp.syncGetString(url);
    }
}
