package viviant.cn.weeklyplan;

import android.app.IntentService;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

import viviant.cn.weeklyplan.bus.NetStatusEvent;
import viviant.cn.weeklyplan.factory.OkHttpFactory;
import viviant.cn.weeklyplan.util.NetWorkUtils;


/**
 * 类描述：
 * 创建人：hww
 * 创建时间：2016/5/03 10:42
 * 修改人：Administrator
 * 修改时间：2015/5/03 10:42
 * 修改备注：
 */
public class ApiService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public ApiService() {
        super("ApiService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (!NetWorkUtils.isNetworkConnected(getApplicationContext())) {
            //在baseActivity里注册即可
            EventBus.getDefault().post(new NetStatusEvent(NetStatusEvent.Please_Connect_To_The_Network_And_Try_Again));
        } else {
            OkHttpFactory.createHttp(this, intent.getType(), intent.getExtras()).execute();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
