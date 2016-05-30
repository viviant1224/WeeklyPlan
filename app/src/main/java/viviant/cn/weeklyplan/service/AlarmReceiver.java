package viviant.cn.weeklyplan.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import viviant.cn.weeklyplan.MainActivity;
import viviant.cn.weeklyplan.R;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.constant.Constants;

/**
 * Created by weiwei.huang on 16-5-30.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        Planthing planthing = (Planthing)intent.getSerializableExtra(Constants.NOTIFICATION_PLAN_THING);

        mBuilder.setContentTitle(planthing.getPlanthingName())//设置通知栏标题
                .setContentText(planthing.getPlanthingDescription())
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL, context)) //设置通知栏点击意图
//  .setNumber(number) //设置通知集合的数量
                .setTicker("该做事了") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
//  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                        //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON

        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        mNotificationManager.notify(Integer.parseInt(planthing.getId()+""), mBuilder.build());

        Toast.makeText(context, "闹铃响了, 可以做点事情了~~", Toast.LENGTH_LONG).show();
    }

    public PendingIntent getDefalutIntent(int flags, Context context){
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(context, 1, intent, flags);
        return pendingIntent;
    }

}
