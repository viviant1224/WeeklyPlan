package viviant.cn.weeklyplan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Switch;

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
import java.util.List;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;
import viviant.cn.weeklyplan.bean.Level;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.bean.Role;
import viviant.cn.weeklyplan.bean.Tag;
import viviant.cn.weeklyplan.constant.Constants;
import viviant.cn.weeklyplan.db.LevelDBManager;
import viviant.cn.weeklyplan.db.RoleDBManager;
import viviant.cn.weeklyplan.db.TagDBManager;
import viviant.cn.weeklyplan.service.NotificationUtil;
import viviant.cn.weeklyplan.service.PlanthingData;
import viviant.cn.weeklyplan.util.DateUtil;



/**
 * Created by weiwei.huang on 16-5-5.
 */
public class WritePlanActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{

    private BootstrapButton writePlanBut;


    private BootstrapButton pickTimeBut;
    private BootstrapButton pickDateBut;


    private BootstrapEditText planthingName;
    private BootstrapEditText planthingDesc;

    private BootstrapButton sendNotificationButton;
    private Switch flagRemindBtn;

    private Spinner planRoleSpinner;
    private ArrayAdapter<Role> adapterRole = null;
    private List<Role> dataRole = null;

    private Spinner planLevelSpinner;
    private ArrayAdapter<Level> adapteLevel=null;
    private List<Level> dataLevel=null;//定义一个集合数据


    private List<Tag> tagList = null;



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
        flagRemindBtn = (Switch)findViewById(R.id.flag_remind);
        pickTimeBut = (BootstrapButton)findViewById(R.id.pick_time_but);
        pickDateBut = (BootstrapButton)findViewById(R.id.pick_date_but);
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
        tagList = new ArrayList<Tag>();
        final List<Tag> tagList = new TagDBManager().loadAll();
        for (int i = 0;i < tagList.size(); i++) {
            final BootstrapButton bbtn = new BootstrapButton(this);
            bbtn.setButtonMode(ButtonMode.CHECKBOX);
            bbtn.setShowOutline(true);
            bbtn.setText(tagList.get(i).getTagName());
            bbtn.setId(Integer.parseInt(tagList.get(i).getId() + ""));
            bbtn.setBootstrapBrand(getRandomBootstrapBrand());

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

    private void writePlanthing() {
        if (checkoutInfo()) {
            if (checkDateAndTime()) {
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
                                mPlanthing.setFlagRemind(flagRemindBtn.isChecked());
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
                                    if (flagRemindBtn.isChecked()) {
                                        NotificationUtil.setNotification(getApplicationContext(),mPlanthing);
                                    }
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
                        .setTitleText("Time select isn't correct!")
                        .setContentText("start Time must less endTime And startTime must bigger than currentTime")
                        .setConfirmText("ok")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        })
                        .show();
            }

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
    }

    private void pickTime() {
        Calendar now = Calendar.getInstance();
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

            }
        });
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    private void pickDate() {
        Calendar now = Calendar.getInstance();
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
    }

    private class butOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.write_plan_button:
                    writePlanthing();
                    break;
                case R.id.pick_time_but:
                    pickTime();
                    break;
                case R.id.pick_date_but:
                    pickDate();
                    break;
                default:
                    break;
            }
        }
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


        String butDate = getBaseContext().getString(R.string.select_date_for_but);
        String fromDate = "" + year+ "-" + (monthOfYear+1) + "-" + dayOfMonth;
        String toDate = "" + yearEnd+ "-" + (monthOfYearEnd+1) + "-" + dayOfMonthEnd;
        butDate = String.format(butDate, fromDate, toDate);
        pickDateBut.setText(butDate);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String hourStringEnd = hourOfDayEnd < 10 ? "0"+hourOfDayEnd : ""+hourOfDayEnd;
        String minuteStringEnd = minuteEnd < 10 ? "0"+minuteEnd : ""+minuteEnd;

        String butTime = getBaseContext().getString(R.string.select_time_for_but);
        String fromTime = "" + hourOfDay+ " :" + minute;
        String toTime = "" + hourStringEnd+ " :" + minuteEnd;
        butTime = String.format(butTime, fromTime, toTime);

        startHour = hourOfDay;
        startMinute = minute;

        endHour = hourOfDayEnd;
        endMinute = minuteEnd;
        hasCheckTime = true;

        pickTimeBut.setText(butTime);
    }

    private boolean checkoutInfo() {
        boolean flag = false;
        if (planthingName.getText().toString().trim().length() != 0 && planthingDesc.getText().toString().trim().length() != 0&&
                hasCheckTime && hasCheckDate) {
            flag = true;
        }
        return flag;
    }

    private boolean checkDateAndTime() {
        boolean flag = false;

        String endTime = getEndTime();
        String startTime = getStartTime();

        flag = DateUtil.compareDate(startTime, endTime) && DateUtil.compareDate(DateUtil.getCurrentTime(), startTime);

        return flag;
    }

    private String getEndTime() {
        return endYear + "-" + (endMonth+1) + "-" + endDay + " " + endHour + ":" + endMinute;
    }

    private String getStartTime() {
        return startYear + "-" + (startMonth+1) + "-" + startDay + " " + startHour + ":" + startMinute;
    }



}
