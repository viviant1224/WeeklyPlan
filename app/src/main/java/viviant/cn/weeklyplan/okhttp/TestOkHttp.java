package viviant.cn.weeklyplan.okhttp;

import android.support.annotation.NonNull;



import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.sql.SQLException;


import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import viviant.cn.weeklyplan.bean.Userinfo;
import viviant.cn.weeklyplan.db.UserinfoDBManager;

/**
 * Created by weiwei.huang on 16-5-4.
 */
public class TestOkHttp extends AbstractBaseOkHttp {


    @Override
    public String getUrl() {
        return "Http://www.baidu.com";
    }

    @Override
    public Request getRequest() {
        return getRequestBuilder().url(getUrl()).build();
    }

    /**
     * 没有body
     *
     * @return
     */
    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public void onSuccess(@NonNull Response response) {
        try {
            String strResponse = response.body().string();
            Userinfo userinfo = new Userinfo();
            userinfo.setUsername(strResponse);
            //插入数据库
            boolean success = new UserinfoDBManager().insert(userinfo);
            if (success) {
                //通知前台更新
                EventBus.getDefault().post(userinfo);
            } else {
                onFailed(new SQLException(strResponse));
            }
//            Logger.d(strResponse);
        } catch (IOException e) {
            onFailed(e);
        }
    }

}
