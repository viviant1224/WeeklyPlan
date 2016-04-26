package viviant.cn.weeklyplan.model;

/**
 * Created by weiwei.huang on 16-3-31.
 */
public class WeekPlan {
    private String timeText;
    private String desctext;

    public WeekPlan (String timeText, String desctext) {
        this.timeText = timeText;
        this.desctext = desctext;
    }

    public String getDesctext() {
        return desctext;
    }

    public void setDesctext(String desctext) {
        this.desctext = desctext;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }

    public String getTimeText() {
        return timeText;
    }
}
