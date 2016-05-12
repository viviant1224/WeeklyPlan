package viviant.cn.weeklyplan;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import viviant.cn.weeklyplan.bean.Level;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.constant.Constants;
import viviant.cn.weeklyplan.db.LevelDBManager;
import viviant.cn.weeklyplan.db.PlanthingDBManager;
import viviant.cn.weeklyplan.service.PlanthingData;

/**
 * Created by weiwei.huang on 16-5-5.
 */
public class WritePlanActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{

    private BootstrapButton writePlanBut;

    private TextView timeView;

    private BootstrapButton pickTimeBut;

    private TextView dateView;

    private BootstrapButton pickDateBut;

//    private BootstrapButton weiboLogin;

    private BootstrapButton sendNotificationButton;

    private Spinner planLevelSpinner;
    private ArrayAdapter<CharSequence> adapteLevel=null;
    private List<CharSequence> dataLevel=null;//定义一个集合数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.write_plan_table);
        writePlanBut = (BootstrapButton)findViewById(R.id.write_plan_button);

        timeView = (TextView)findViewById(R.id.time_textview);
        pickTimeBut = (BootstrapButton)findViewById(R.id.pick_time_but);

        dateView = (TextView)findViewById(R.id.date_textview);
        pickDateBut = (BootstrapButton)findViewById(R.id.pick_date_but);
//        weiboLogin = (BootstrapButton)findViewById(R.id.weibo_login_button);
        sendNotificationButton = (BootstrapButton)findViewById(R.id.send_notification_button);
        planLevelSpinner = (Spinner)findViewById(R.id.plan_level);
        planLevelSpinner.setPrompt("Level Select");

        dataLevel = new ArrayList<CharSequence>();
        List<Level> levelList = new LevelDBManager().loadAll();
        for (int i = 0;i < levelList.size(); i++) {
            dataLevel.add(levelList.get(i).getLevelName());
        }



        adapteLevel = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,dataLevel);
        adapteLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        planLevelSpinner.setAdapter(this.adapteLevel);

        writePlanBut.setOnClickListener(new butOnClickListener());
        pickTimeBut.setOnClickListener(new butOnClickListener());
        pickDateBut.setOnClickListener(new butOnClickListener());
//        weiboLogin.setOnClickListener(new butOnClickListener());
        sendNotificationButton.setOnClickListener(new butOnClickListener());
    }

    private class butOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Calendar now = Calendar.getInstance();
            switch (v.getId()) {
                case R.id.write_plan_button:

                    new SweetAlertDialog(WritePlanActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure?")
                            .setContentText("Won't be able to recover this file!")
                            .setConfirmText("Yes,Commit it!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {

                                    Planthing mPlanthing = new Planthing();
                                    mPlanthing.setPlanthingDescription("desc1");
                                    mPlanthing.setPlanthingName("planthingnameVCX");
                                    mPlanthing.setState(0);
                                    mPlanthing.setLevelId(1);
                                    mPlanthing.setDoDateTime("2016-05-12 16:36");
                                    mPlanthing.setEndDateTime("2016-05-12 19:56");
                                    mPlanthing.setFlagRemind(true);
                                    mPlanthing.setRoleId(2);
                                    mPlanthing.setTagId(2);
                                    mPlanthing.setUserinfoPId(1);
                                    boolean isSuccess = new PlanthingData().insertPlanthing(mPlanthing);
                                    if (isSuccess) {
                                        sDialog
                                                .setTitleText("Commit!")
                                                .setContentText("Your plan is in databases!")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        Intent mIntent = new Intent(WritePlanActivity.this, MainActivity.class);
                                        Bundle mBundle = new Bundle();
                                        mBundle.putSerializable(Constants.INTENT_PLAN_THING, mPlanthing);
                                        mIntent.putExtras(mBundle);
                                        startActivity(mIntent);
                                    } else {
                                        sDialog
                                                .setTitleText("Failed!")
                                                .setContentText("Your plan isn't in databases!")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    }

                                }
                            })
                            .show();
                    break;
                case R.id.pick_time_but:

                    TimePickerDialog tpd = TimePickerDialog.newInstance(
                            WritePlanActivity.this,
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            true//is use 24mode
                    );
                    tpd.setThemeDark(false);
                    tpd.vibrate(false);
                    tpd.dismissOnPause(false);
                    tpd.enableSeconds(false);
//                    if (modeCustomAccentTime.isChecked()) {
//                    getResources(R.color.colorPrimary)
                        tpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
//                    }
//                    if (titleTime.isChecked()) {
                        tpd.setTitle("StartTime");
//                    }
//                    if (limitTimes.isChecked()) {
//                        tpd.setTimeInterval(2, 5, 10);
//                    }
                    tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            Log.d("TimePicker", "Dialog was cancelled");
                        }
                    });
                    tpd.show(getFragmentManager(), "Timepickerdialog");
                    break;
                case R.id.pick_date_but:
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            WritePlanActivity.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setThemeDark(false);
                    dpd.vibrate(false);
                    dpd.dismissOnPause(false);
                    dpd.showYearPickerFirst(false);
                    dpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
                    dpd.setTitle("Date time");
                    dpd.show(getFragmentManager(), "Datepickerdialog");
                    break;
//                case R.id.weibo_login_button:
//
//                    break;
                case R.id.send_notification_button:
                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());


                    mBuilder.setContentTitle("测试标题")//设置通知栏标题
                            .setContentText("测试内容")
                    .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
//  .setNumber(number) //设置通知集合的数量
                        .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
                        .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                        .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
//  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                        .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                        .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                        .setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON

                    Notification notification = mBuilder.build();
                    notification.flags = Notification.FLAG_AUTO_CANCEL;

                    mNotificationManager.notify(1, mBuilder.build());
                    break;
                default:
                    break;
            }
        }
    }

    public PendingIntent getDefalutIntent(int flags){
        Intent intent = new Intent(getBaseContext(),MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, intent, flags);
        return pendingIntent;
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String secondString = second < 10 ? "0"+second : ""+second;
        String time = "You picked the following time: "+hourString+"h"+minuteString+"m"+secondString+"s";
        pickTimeBut.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        pickDateBut.setText(date);
    }

}
