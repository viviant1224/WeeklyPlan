package viviant.cn.weeklyplan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import viviant.cn.weeklyplan.R;

/**
 * Created by weiwei.huang on 16-3-24.
 */
public class HistroyPlanListViewAdapter extends BaseAdapter {
    private LayoutInflater listContainer;//视图容器
    private Context context;
    private List<Map<String, Object>> listItems;

    public final class ListItemView{                //自定义控件集合
        public TextView timeText;
        public TextView descText;
        public View planState;
    }

    public HistroyPlanListViewAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;

    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("weiwei", "getView--");
        ListItemView  listItemView = null;
        if (view == null) {
            listItemView = new ListItemView();
            Log.d("weiwei", "getView");
            view = listContainer.inflate(R.layout.histroy_plan_listview_item,null);
            listItemView.timeText = (TextView)view.findViewById(R.id.plan_time);
            listItemView.descText = (TextView)view.findViewById(R.id.plan_desc);
            listItemView.planState = (View)view.findViewById(R.id.plan_time);

            view.setTag(listItemView);
        } else {
            listItemView = (ListItemView)view.getTag();
        }
        return view;
    }
}
