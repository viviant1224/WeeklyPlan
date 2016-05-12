package viviant.cn.weeklyplan;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.db.PlanthingDBManager;

/**
 * Created by weiwei.huang on 16-5-5.
 */
public class PlanInfoActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{

    private BootstrapButton writePlanBut;

    private TextView timeView;

    private BootstrapButton pickTimeBut;

    private TextView dateView;

    private BootstrapButton pickDateBut;

    private BootstrapButton weiboLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plan);
        writePlanBut = (BootstrapButton)findViewById(R.id.write_plan_button);

        timeView = (TextView)findViewById(R.id.time_textview);
        pickTimeBut = (BootstrapButton)findViewById(R.id.pick_time_but);

        dateView = (TextView)findViewById(R.id.date_textview);
        pickDateBut = (BootstrapButton)findViewById(R.id.pick_date_but);
        weiboLogin = (BootstrapButton)findViewById(R.id.weibo_login_button);

        writePlanBut.setOnClickListener(new butOnClickListener());
        pickTimeBut.setOnClickListener(new butOnClickListener());
        pickDateBut.setOnClickListener(new butOnClickListener());
        weiboLogin.setOnClickListener(new butOnClickListener());

        Planthing mPlanthing = (Planthing)getIntent().getSerializableExtra("mPlanthing");


        if (mPlanthing != null) {

            Log.d("weiwei", "planname : " + mPlanthing.getPlanthingDescription());
        }
    }

    private class butOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Calendar now = Calendar.getInstance();
            switch (v.getId()) {
                case R.id.write_plan_button:

                    new SweetAlertDialog(PlanInfoActivity.this, SweetAlertDialog.WARNING_TYPE)
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
                                    mPlanthing.setDoDateTime("2016-05-10 16:36");
                                    mPlanthing.setEndDateTime("2016-05-10 19:56");
                                    mPlanthing.setFlagRemind(true);
                                    mPlanthing.setRoleId(2);
                                    mPlanthing.setTagId(2);
                                    mPlanthing.setUserinfoPId(1);
                                    boolean isSuccess = PlanthingDBManager.getPlanthingDBManager().insert(mPlanthing);
                                    if (isSuccess) {
                                        sDialog
                                                .setTitleText("Commit!")
                                                .setContentText("Your plan is in databases!")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        Intent mIntent = new Intent(PlanInfoActivity.this, MainActivity.class);
                                        Bundle mBundle = new Bundle();
                                        mBundle.putSerializable("mPlanthing", mPlanthing);
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
                            PlanInfoActivity.this,
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
                            PlanInfoActivity.this,
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
                case R.id.weibo_login_button:

                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String secondString = second < 10 ? "0"+second : ""+second;
        String time = "You picked the following time: "+hourString+"h"+minuteString+"m"+secondString+"s";
        timeView.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        dateView.setText(date);
    }

}
