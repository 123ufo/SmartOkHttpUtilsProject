package com.ufo.smartokhppt;

import com.ufo.smartokhppt.params.RequestParams;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/17.
 * <p/>
 * 同步okHttp.
 */
class SyncOkHttp extends BaseHttp {

    private static final String TAG = "SyncOkHttp";

    /**
     * 同步 http get请求
     *
     * @param url 请求地址
     * @return 返回string形式的结果
     * @throws IOException
     */
    public String syncGetString(String url) throws IOException {
        return syncGetResponse(url).body().string();
    }

    /**
     * 同步 http get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回string形式的结果
     * @throws IOException
     */
    public String syncGetString(String url, RequestParams params) throws IOException {
        url = getRealUrl(url, params);
        return syncGetString(url);
    }

    /**
     * 同步 http get请求
     *
     * @param url 请求地址
     * @return 返回byte[]形式的结果
     * @throws IOException
     */
    public byte[] syncGetByteArray(String url) throws IOException {
        return syncGetResponse(url).body().bytes();
    }

    /**
     * 同步 http get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回byte[]形式的结果
     * @throws IOException
     */
    public byte[] syncGetByteArray(String url, RequestParams params) throws IOException {
        url = getRealUrl(url, params);
        return syncGetByteArray(url);
    }

    /**
     * 同步 http get请求
     *
     * @param url 请求地址
     * @return 返回Response形式的结果
     * @throws IOException
     */
    public Response syncGetResponse(String url) throws IOException {
//        return syncRequest(new Request.Builder().url(url).get().build());
        return syncRequest(createRequest(url, null, Method.GET));
    }

    /**
     * 同步 http get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回Response形式的结果
     * @throws IOException
     */
    public Response syncGetResponse(String url, RequestParams params) throws IOException {
        url = getRealUrl(url, params);
        return syncGetResponse(url);
    }

/**--------------------------------------   post    -----------------------------------------*/
    /**
     * 同步 http post请求
     *
     * @param url 请求地址
     * @return 返回string形式的结果
     * @throws IOException
     */
    public String syncPostString(String url) throws IOException {
        return syncPostResponse(url).body().string();
    }

    /**
     * 同步 http post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回 返回string形式的结果
     * @throws IOException
     */
    public String syncPostString(String url, RequestParams params) throws IOException {
        return syncPostResponse(url, params).body().string();
    }

    /**
     * 同步 http post请求
     *
     * @param url 请求地址
     * @return 返回 byte[]类型
     * @throws IOException
     */
    public byte[] syncPostByteArray(String url) throws IOException {
        return syncPostResponse(url).body().bytes();
    }

    /**
     * 同步 http post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回 byte[]类型
     * @throws IOException
     */
    public byte[] syncPostByteArray(String url, RequestParams params) throws IOException {
        return syncPostResponse(url, params).body().bytes();
    }

    /**
     * 同步 http post请求
     *
     * @param url 请求地址
     * @return 返回Response形式的结果
     * @throws IOException
     */
    public Response syncPostResponse(String url) throws IOException {
        return syncPostResponse(url, null);
    }

    /**
     * 同步 http post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 返回Response形式的结果
     * @throws IOException
     */
    public Response syncPostResponse(String url, RequestParams params) throws IOException {
//        FormBody formBody = getFormBody(params);
//        return syncRequest(new Request.Builder().url(url).post(formBody).build());
        return syncRequest(createRequest(url, params, Method.POST));
    }


}
