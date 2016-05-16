package viviant.cn.weeklyplan;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;

import java.util.Calendar;

/**
 * Created by weiwei.huang on 16-5-16.
 */
public class SettingActivity extends AppCompatActivity {
    private TextView dateTextView;
    private TextView timeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        // Find our View instances
        dateTextView = (TextView)findViewById(R.id.date_textview);
        timeTextView = (TextView)findViewById(R.id.time_textview);
        BootstrapButton dateButton = (BootstrapButton)findViewById(R.id.pick_date_but);
        BootstrapButton timeButton = (BootstrapButton)findViewById(R.id.pick_time_but);
    }

}
