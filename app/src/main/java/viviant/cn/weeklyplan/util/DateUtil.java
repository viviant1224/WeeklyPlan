package viviant.cn.weeklyplan.util;


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
}
