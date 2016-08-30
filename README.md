##一个基于Okhttp进行封装的http请求libs,以极简单的操作.实现http请求.包括post get 下载 上传.同步,异步,添加拦截器,取消请求等


    private void get1() {
        OkHttpUtils.asyncGet(url, new StringCallback() {
            @Override
            public void onSuccess(Call call, String result) {
                Log.d("get1", result);
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("get1", e.toString());
            }
        });
    }

    private void get2() {
        RequestParams params = new RequestParams();
        params.put("id", "1");
        OkHttpUtils.asyncGet(url1, params, new StringCallback() {
            @Override
            public void onSuccess(Call call, String result) {
                Log.d("get2", result);
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("get2", e.toString());
            }
        });
    }


    private void post1() {
        OkHttpUtils.asyncPost(url, new StringCallback() {
            @Override
            public void onSuccess(Call call, String result) {
                Log.d("post1", result);
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("post1", e.toString());
            }
        });
    }

    private void post2() {
        RequestParams params = new RequestParams();
        params.put("id", "1");
        OkHttpUtils.asyncPost(url1, params, new StringCallback() {
            @Override
            public void onSuccess(Call call, String result) {
                Log.d("post2", result);
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("post2", e.toString());
            }
        });
    }

    private void download() {
        File file = new File("sdcard/test_download.jpg");
        String downloadUrl = "http://imgstore.cdn.sogou.com/app/a/100540002/713179.jpg";
        OkHttpUtils.asyncPost(downloadUrl, new DownloadCallback(file) {
            @Override
            public void onSuccess(Call call, File file) {
                Log.d("download", file.length() + "");
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("download failed", e.toString());
            }

            @Override
            public void onProgress(long total, int progress) {
                Log.d("download progress", progress + "");
            }
        });
    }

    private void upload() {
        File file = new File("sdcard/test_download.jpg");
        if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
        //请自已填写上传的地址
        OkHttpUtils.uploadFile("上传的rul", "image/png", file, new UploadFileCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
                Log.d("upload", e.toString());
            }

            @Override
            public void onSuccess(Call call, String result) {
                Log.d("upload", result);
            }

            @Override
            public void onProgress(long total, long progress) {
                Log.d("onProgress", progress + "");
            }
        });
    }

    private void cancel() {
        OkHttpUtils.cancelAllRequest();
    }

    /**
     * 添加拦截器
     */
    private void addInterceptor() {
        OkHttpUtils.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                //do some thinking

                return chain.proceed(chain.request());
            }
        });
    }


