package viviant.cn.weeklyplan.util;


import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by viviant on 2015/4/28.
 * Date Util
 */
public class DateUtil {
    public static  int getCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        int currentWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        return currentWeekOfYear;
    }
    public static int getDay() {
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        cal.setTime(today);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return false;
            } else if (dt1.getTime() < dt2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static int getTimeHour(String time) {
        return Integer.parseInt(time.split(" ")[1].split(":")[0]);
    }

    public static int getTimeMinute(String time) {
        return Integer.parseInt(time.split(" ")[1].split(":")[1]);
    }

    public static int getTimeMonth(String time) {
        return Integer.parseInt(time.split(" ")[0].split("-")[1]);
    }

    public static int getTimeYear(String time) {
        return Integer.parseInt(time.split(" ")[0].split("-")[0]);
    }

    public static int getTimeDay(String time) {
        return Integer.parseInt(time.split(" ")[0].split("-")[2]);
    }

    public static Calendar getCalendar(String time) {
        try {
    //      2016-05-10 16:36
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date =sdf.parse(time);
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date);

            return calendar;
        } catch (Exception e) {
            Log.d("weiwei","time translate error");
            e.printStackTrace();
            return null;
        }
    }

    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        cal.setTime(today);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static String getCurrentTime() {
        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateTime = sdf.format(date);
        return dateTime;
    }

}
