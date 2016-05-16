package viviant.cn.weeklyplan;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand;
import com.beardedhen.androidbootstrap.api.defaults.ButtonMode;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;
import viviant.cn.weeklyplan.bean.Level;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.bean.Role;
import viviant.cn.weeklyplan.bean.Tag;
import viviant.cn.weeklyplan.constant.Constants;
import viviant.cn.weeklyplan.db.LevelDBManager;
import viviant.cn.weeklyplan.db.PlanthingDBManager;
import viviant.cn.weeklyplan.db.RoleDBManager;
import viviant.cn.weeklyplan.db.TagDBManager;
import viviant.cn.weeklyplan.service.PlanthingData;

/**
 * Created by weiwei.huang on 16-5-5.
 */
public class WritePlanActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{

    private BootstrapButton writePlanBut;


    private BootstrapButton pickTimeBut;
    private BootstrapButton pickDateBut;

//    private BootstrapButton weiboLogin;

    private BootstrapEditText planthingName;
    private BootstrapEditText planthingDesc;

    private BootstrapButton sendNotificationButton;
    private BootstrapButton flagRemindBtn;

    private Spinner planRoleSpinner;
    private ArrayAdapter<Role> adapterRole = null;
    private List<Role> dataRole = null;

    private Spinner planLevelSpinner;
    private ArrayAdapter<Level> adapteLevel=null;
    private List<Level> dataLevel=null;//定义一个集合数据


    private int startYear;
    private int startMonth;
    private int startDay;
    private int startHour;
    private int startMinute;

    private boolean hasCheckDate = false;
    private boolean hasCheckTime = false;


    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.write_plan);
        writePlanBut = (BootstrapButton)findViewById(R.id.write_plan_button);

        planthingName = (BootstrapEditText)findViewById(R.id.planthing_name);
        planthingDesc = (BootstrapEditText)findViewById(R.id.planthing_desc);
        flagRemindBtn = (BootstrapButton)findViewById(R.id.flag_remind);





        pickTimeBut = (BootstrapButton)findViewById(R.id.pick_time_but);

        pickDateBut = (BootstrapButton)findViewById(R.id.pick_date_but);
//        weiboLogin = (BootstrapButton)findViewById(R.id.weibo_login_button);
        sendNotificationButton = (BootstrapButton)findViewById(R.id.send_notification_button);


        planRoleSpinner = (Spinner)findViewById(R.id.plan_role);
        planRoleSpinner.setPrompt("Role Select");
        dataRole = new ArrayList<Role>();
        List<Role> roleList = new RoleDBManager().loadAll();
        for (int j = 0;j < roleList.size(); j++) {
            dataRole.add(roleList.get(j));
        }
        adapterRole = new ArrayAdapter<Role>(this, android.R.layout.simple_spinner_item,dataRole);
        adapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        planRoleSpinner.setAdapter(this.adapterRole);


        planLevelSpinner = (Spinner)findViewById(R.id.plan_level);
        planLevelSpinner.setPrompt("Level Select");
        dataLevel = new ArrayList<Level>();
        List<Level> levelList = new LevelDBManager().loadAll();
        for (int i = 0;i < levelList.size(); i++) {
            dataLevel.add(levelList.get(i));
        }
        adapteLevel = new ArrayAdapter<Level>(this, android.R.layout.simple_spinner_item, dataLevel);
        adapteLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        planLevelSpinner.setAdapter(this.adapteLevel);

        createTags();



        writePlanBut.setOnClickListener(new butOnClickListener());
        pickTimeBut.setOnClickListener(new butOnClickListener());
        pickDateBut.setOnClickListener(new butOnClickListener());
        sendNotificationButton.setOnClickListener(new butOnClickListener());
    }

    private void createTags() {
        GridLayout layout = (GridLayout)findViewById(R.id.grid_layout);
        List<Tag> tagList = new TagDBManager().loadAll();

        for (int i = 0;i < tagList.size(); i++) {
            BootstrapButton bbtn = new BootstrapButton(this);
            bbtn.setButtonMode(ButtonMode.CHECKBOX);
            bbtn.setShowOutline(true);
            bbtn.setText(tagList.get(i).getTagName());
            bbtn.setId(Integer.parseInt(tagList.get(i).getId() + ""));
            bbtn.setBootstrapBrand(getRandomBootstrapBrand ());
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(10,10,10,10);
            bbtn.setLayoutParams(params);

            layout.addView(bbtn);
        }
    }

    private static BootstrapBrand getRandomBootstrapBrand () {
        List<BootstrapBrand> bootstrapBrandList = new ArrayList<BootstrapBrand>();
        BootstrapBrand b1 = DefaultBootstrapBrand.INFO;
        BootstrapBrand b2 = DefaultBootstrapBrand.DANGER;
        BootstrapBrand b3 = DefaultBootstrapBrand.WARNING;
        BootstrapBrand b4 = DefaultBootstrapBrand.PRIMARY;
        BootstrapBrand b5 = DefaultBootstrapBrand.SUCCESS;
        BootstrapBrand b6 = DefaultBootstrapBrand.REGULAR;
        BootstrapBrand b7 = DefaultBootstrapBrand.SECONDARY;
        bootstrapBrandList.add(b1);
        bootstrapBrandList.add(b2);
        bootstrapBrandList.add(b3);
        bootstrapBrandList.add(b4);
        bootstrapBrandList.add(b5);
        bootstrapBrandList.add(b6);
        bootstrapBrandList.add(b7);


        Random rand = new Random();
        int i = rand.nextInt(); //int范围类的随机数
        i = rand.nextInt(6); //生成0-100以内的随机数

        i = (int)(Math.random() * 6); //0-100以内的随机数，用Matn.random()方式

        return bootstrapBrandList.get(i);
    }


    private class
            butOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Calendar now = Calendar.getInstance();
            switch (v.getId()) {
                case R.id.write_plan_button:

                    if (checkoutInfo()) {
                        new SweetAlertDialog(WritePlanActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Are you sure?")
                                .setContentText("Won't be able to recover this file!")
                                .setConfirmText("Yes,Commit it!")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {

                                        Level level = (Level)planLevelSpinner.getSelectedItem();

                                        Role role = (Role)planRoleSpinner.getSelectedItem();

                                        Planthing mPlanthing = new Planthing();
                                        mPlanthing.setPlanthingDescription(planthingDesc.getText().toString());
                                        mPlanthing.setPlanthingName(planthingName.getText().toString());
                                        mPlanthing.setState(0);
                                        mPlanthing.setLevelId(level.getId());
                                        mPlanthing.setDoDateTime(getStartTime());
                                        mPlanthing.setEndDateTime(getEndTime());
                                        mPlanthing.setFlagRemind(flagRemindBtn.isMustBeSelected());
                                        mPlanthing.setRoleId(role.getId());
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
                    } else {
                        new SweetAlertDialog(WritePlanActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Warnning?")
                                .setContentText("some column must be comeplete!")
                                .setConfirmText("ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                    }
                                })
                                .show();
                    }
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
                    tpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
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
                    dpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
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
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth,int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {

        startYear = year;
        startMonth = monthOfYear;
        startDay = dayOfMonth;

        endDay = dayOfMonthEnd;
        endMonth = monthOfYearEnd;
        endYear = yearEnd;
        hasCheckDate = true;
//        String date = "You picked the following date: From- "+dayOfMonth+"/"+(++monthOfYear)+"/"+year+" To "+dayOfMonthEnd+"/"+(++monthOfYearEnd)+"/"+yearEnd;
//        pickDateBut.setText(date);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String hourStringEnd = hourOfDayEnd < 10 ? "0"+hourOfDayEnd : ""+hourOfDayEnd;
        String minuteStringEnd = minuteEnd < 10 ? "0"+minuteEnd : ""+minuteEnd;
        String time = "You picked the following time: From - "+hourOfDay+"h"+minute+" To - "+hourOfDayEnd+"h"+minuteEnd;

        startHour = hourOfDay;
        startMinute = minute;

        endHour = hourOfDayEnd;
        endMinute = minuteEnd;
        hasCheckTime = true;

        pickTimeBut.setText(time);
    }

    private boolean checkoutInfo() {
        boolean flag = false;
        if (planthingName.getText().toString().trim().length() != 0 && planthingDesc.getText().toString().trim().length() != 0&&
                hasCheckTime && hasCheckDate) {
            flag = true;
        }
        return flag;

    }

    private String getEndTime() {
        return endYear + "-" + (endMonth+1) + "-" + endDay + " " + endHour + ":" + endMinute;
    }

    private String getStartTime() {
        return startYear + "-" + (startMonth+1) + "-" + startDay + " " + startHour + ":" + startMinute;
    }



}
