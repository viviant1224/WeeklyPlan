package viviant.cn.weeklyplan.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import viviant.cn.weeklyplan.MainActivity;
import viviant.cn.weeklyplan.R;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.constant.Constants;
import viviant.cn.weeklyplan.preference.WeeklyPlanSharePreference;

/**
 * Created by weiwei.huang on 16-5-30.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String isOpenNotification = "false";
        try {
            isOpenNotification = testAccessPreference();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isOpenNotification.equals("true")) {
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



    }


    public String testAccessPreference() throws Exception{
        String path = "/data/data/viviant.cn.weeklyplan/shared_prefs/weeklyplan.xml";

        String isOpenNotification = "false";
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        ByteArrayOutputStream outStream =new ByteArrayOutputStream();
        byte[] buffer =new byte[1024];
        int len =0;
        while((len=inputStream.read(buffer))!=-1){
            outStream.write(buffer,0,len);
        }
        byte [] data =outStream.toByteArray();

        String fileContent = new String(data);
        String [] arrays = fileContent.split("\n");

        for (int i = 0; i < arrays.length; i++ ) {
            if (arrays[i].contains("isOpenNotification")) {
                String [] values = arrays[i].split("\"");
                isOpenNotification =  values[3];
            }
        }
        return isOpenNotification;

    }




    public PendingIntent getDefalutIntent(int flags, Context context){
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(context, 1, intent, flags);
        return pendingIntent;
    }

}
