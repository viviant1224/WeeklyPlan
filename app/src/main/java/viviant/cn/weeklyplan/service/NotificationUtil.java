package viviant.cn.weeklyplan.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.constant.Constants;
import viviant.cn.weeklyplan.util.DateUtil;

/**
 * Created by weiwei.huang on 16-5-30.
 */
public class NotificationUtil {
    public static void setNotification(Context context, Planthing planthing) {

        Intent intent = new Intent(context, AlarmReceiver.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(Constants.NOTIFICATION_PLAN_THING, planthing);
        intent.putExtras(mBundle);

        PendingIntent sender = PendingIntent.getBroadcast(context, Integer.parseInt(planthing.getId()+""), intent, 0);
        // 进行闹铃注册
        AlarmManager manager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, DateUtil.translateDateTime(planthing.getDoDateTime()), sender);

        Toast.makeText(context, "设置通知成功!", Toast.LENGTH_LONG).show();

    }
}
