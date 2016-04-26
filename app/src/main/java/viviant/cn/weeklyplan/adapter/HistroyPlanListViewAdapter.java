package viviant.cn.weeklyplan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import viviant.cn.weeklyplan.R;
import viviant.cn.weeklyplan.model.WeekPlan;

/**
 * Created by weiwei.huang on 16-3-24.
 */
public class HistroyPlanListViewAdapter extends BaseAdapter {
    private LayoutInflater listContainer;//视图容器
    private Context context;
    private List<WeekPlan> listItems;


    public HistroyPlanListViewAdapter(Context context, List<WeekPlan> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;

    }

    @Override
    public int getCount() {
        return (listItems == null ? 0 : listItems.size());
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class  ViewHolder{ //自定义控件集合
        TextView timeText;
        TextView descText;
        CircleImageView planState;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("weiwei", "getView--");
        final WeekPlan weekPlan = (WeekPlan)getItem(i);
        ViewHolder  viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            Log.d("weiwei", "getView");
            view = listContainer.inflate(R.layout.histroy_plan_listview_item,null);
            viewHolder.timeText = (TextView)view.findViewById(R.id.plan_time);
            viewHolder.descText = (TextView)view.findViewById(R.id.plan_desc);
            viewHolder.planState = (CircleImageView)view.findViewById(R.id.plan_state);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.timeText.setText(weekPlan.getTimeText());
        viewHolder.descText.setText(weekPlan.getDesctext());
        return view;
    }


}
