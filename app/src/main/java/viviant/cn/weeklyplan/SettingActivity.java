package viviant.cn.weeklyplan;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;

import java.util.Calendar;

import viviant.cn.weeklyplan.preference.WeeklyPlanSharePreference;


/**
 * Created by weiwei.huang on 16-5-16.
 */
public class SettingActivity extends AppCompatActivity {

    private Switch notificationSwitch;
//    private Spinner notificationSpinner;
//    private Switch sendByEmailSwitch;
//    private BootstrapEditText emailInput;
//    private BootstrapButton emailButton;
//    private Switch sendByMmsSwitch;
//    private BootstrapEditText mmsInput;
//    private BootstrapButton mmsButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        findViews();
        boolean isOpenNotification = WeeklyPlanSharePreference.loadNotification(getApplicationContext());

        if (isOpenNotification) {
            notificationSwitch.setChecked(true);
        }
    }

    private class onButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.notification_switch:
                    if (notificationSwitch.isChecked()) {

                        setNotificationEnable();
                        WeeklyPlanSharePreference.setNotificationSwitch(getApplicationContext(), true);

                    } else {
                        setNotificationDisable();

                        WeeklyPlanSharePreference.setNotificationSwitch(getApplicationContext(), false);

                    }
                    break;
//                case R.id.is_send_by_email:
//                    if (sendByEmailSwitch.isChecked()) {
//                        setEmailNotificationdisable();
//                    } else {
//                        setEmailNotificationEnable();
//                    }
//                    break;
//                case R.id.is_send_by_mms:
//                    Log.d("weiwei","mms");
//                    if (sendByMmsSwitch.isChecked()) {
//                        setMmsNotificationdisable();
//                    } else {
//                        setMmsNotificationEnable();
//                    }
//                    break;
            }
        }
    }

    private void setNotificationDisable() {
//        sendByEmailSwitch.setEnabled(false);
//        sendByMmsSwitch.setEnabled(false);
//        emailInput.setEnabled(false);
//        mmsInput.setEnabled(false);
    }

    private void setNotificationEnable() {
//        sendByEmailSwitch.setEnabled(true);
//        sendByMmsSwitch.setEnabled(true);
//        emailInput.setEnabled(true);
//        mmsInput.setEnabled(true);
    }

//    private void setEmailNotificationEnable() {
//        emailButton.setEnabled(true);
//    }
//
//    private void setEmailNotificationdisable() {
//        emailButton.setEnabled(false);
//    }
//
//    private void setMmsNotificationEnable() {
//        mmsButton.setEnabled(true);
//    }
//
//    private void setMmsNotificationdisable() {
//        mmsButton.setEnabled(false);
//    }


    private void findViews() {
        notificationSwitch = (Switch)findViewById(R.id.notification_switch);
//        notificationSpinner = (Spinner)findViewById(R.id.notification_time);
//        sendByEmailSwitch = (Switch)findViewById(R.id.is_send_by_email);
//        emailInput = (BootstrapEditText)findViewById(R.id.email_input);
//        emailButton = (BootstrapButton)findViewById(R.id.change_email_button);
//        sendByMmsSwitch = (Switch)findViewById(R.id.is_send_by_mms);
//        mmsInput = (BootstrapEditText)findViewById(R.id.mms_input);
//        mmsButton = (BootstrapButton)findViewById(R.id.mms_button);


        notificationSwitch.setOnClickListener(new onButtonClickListener());
//        sendByEmailSwitch.setOnClickListener(new onButtonClickListener());
//        emailButton.setOnClickListener(new onButtonClickListener());
//        mmsButton.setOnClickListener(new onButtonClickListener());
    }

}
