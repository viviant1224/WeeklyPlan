package viviant.cn.weeklyplan;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;

import java.util.Calendar;

import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.constant.Constants;

public class PlanInfoActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener
{


    private BootstrapEditText planName;
    private BootstrapEditText planDesc;
    private Planthing mPlanthing = null;

    private BootstrapButton dateButton;
    private BootstrapButton timeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        // Find our View instances


        mPlanthing = (Planthing)getIntent().getSerializableExtra(Constants.INTENT_PLAN_THING_UPDATE);


        getView();
        setViewInfo();


    }

    private void getView() {
        dateButton = (BootstrapButton)findViewById(R.id.pick_date_but_update);
        timeButton = (BootstrapButton)findViewById(R.id.pick_time_but_update);
        planName = (BootstrapEditText)findViewById(R.id.planthing_name_update);
        planDesc = (BootstrapEditText)findViewById(R.id.planthing_desc_update);
    }


    private void setViewInfo() {
        if (mPlanthing != null) {
            planName.setText(mPlanthing.getPlanthingName());
            planDesc.setText(mPlanthing.getPlanthingDescription());
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth,int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        String date = "You picked the following date: From- "+dayOfMonth+"/"+(++monthOfYear)+"/"+year+" To "+dayOfMonthEnd+"/"+(++monthOfYearEnd)+"/"+yearEnd;
        dateButton.setText(date);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String hourStringEnd = hourOfDayEnd < 10 ? "0"+hourOfDayEnd : ""+hourOfDayEnd;
        String minuteStringEnd = minuteEnd < 10 ? "0"+minuteEnd : ""+minuteEnd;
        String time = "You picked the following time: From - "+hourString+"h"+minuteString+" To - "+hourStringEnd+"h"+minuteStringEnd;

        timeButton.setText(time);
    }
}

