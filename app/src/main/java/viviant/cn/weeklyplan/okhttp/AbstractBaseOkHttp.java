package viviant.cn.weeklyplan.okhttp;

import android.content.Context;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import viviant.cn.weeklyplan.exception.e.ErrorMessageFactory;
import viviant.cn.weeklyplan.i.IOkHttpClient;
import viviant.cn.weeklyplan.i.IOkHttpPrintLog;
import viviant.cn.weeklyplan.log.PrintLogUtil;
import viviant.cn.weeklyplan.okhttp.cookies.PersistentCookieJar;
import viviant.cn.weeklyplan.okhttp.cookies.cache.SetCookieCache;
import viviant.cn.weeklyplan.okhttp.cookies.persistence.SharedPrefsCookiePersistor;
import viviant.cn.weeklyplan.util.ExternalStorageUtil;
import viviant.cn.weeklyplan.util.TimeUtils;

/**
 *  类描述： 创建人：hww 创建时间：2016/05/03 14:03 修改人：Administrator
 * 修改时间：2016/5/03 14:03 修改备注：
 */
public abstract class AbstractBaseOkHttp implements IOkHttpClient, IOkHttpPrintLog {

    private static OkHttpClient.Builder okHttpClientBuilder;
    /**
     * get请求参数 Bundle key
     */
    public static final String BUNDLE_GET_KEY = "BUNDLE_GET_KEY";
    /**
     * post/put 请求参数 Bundle key
     */
    public static final String BUNDLE_POST_OR_PUT_KEY = "BUNDLE_POST_OR_PUT_KEY";

    protected Context context;

    protected Bundle bundle;
    /**
     * 网络请求 异常信息
     */
    protected String errorMessage;

    protected String requestTime;
    protected String responseTime;
    protected String requestJson;

    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    /**
     * 获取HttpClient
     *
     * @return
     */
    @Override
    public OkHttpClient getOkHttpClient() {
        okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.cache(new Cache(new File(ExternalStorageUtil.getExternalDownloadPath() + File.separator + "cache.tmp"), cacheSize));
        okHttpClientBuilder.cookieJar(new PersistentCookieJar(new SetCookieCache(),new SharedPrefsCookiePersistor(context)));
        return okHttpClientBuilder.build();
    }

    /**
     * 请求配置
     *
     * @return
     */
    @Override
    public Request.Builder getRequestBuilder() {
        return new Request.Builder().addHeader(HttpConstant.CONTENT_TYPE_KEY, HttpConstant.CONTENT_TYPE).addHeader(HttpConstant.ACCEPT_KEY, HttpConstant.ACCEPT);
    }

    /**
     * 处理接口返回
     *
     * @return
     */
    @Override
    public Callback getResponseCallBack() {
        return new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                onFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    if (response.isSuccessful()) {
                        responseTime = TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒"));
                        onSuccess(response);
                    } else {
                        onFailed(new IOException("onFailed" + response));
                    }
                } catch (IOException e) {
                    onFailed(e);
                }
            }
        };
    }

    /**
     * 如果子类需要处理失败信息 重写该方法
     *
     * @param exception
     */
    @Override
    public void onFailed(Exception exception) {
        responseTime = TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒"));
        errorMessage = ErrorMessageFactory.createMessage(exception);
        printLog();
        // 通知前台更新 失败后返回子类对象 在Activity里注册子类的监听
//        com.orhanobut.logger.Logger.e(errorMessage);
    }

    /**
     * 启动网络请求
     */
    @Override
    public void execute() {
        getOkHttpClient().newCall(getRequest()).enqueue(getResponseCallBack());
        requestTime = TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒"));
    }

    /**
     * 打印日志
     */
    @Override
    public void printLog() {
        PrintLogUtil.createPrintLogToSdCard(context, this);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
