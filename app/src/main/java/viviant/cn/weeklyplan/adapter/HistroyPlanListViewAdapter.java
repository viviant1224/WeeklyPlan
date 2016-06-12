package viviant.cn.weeklyplan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import viviant.cn.weeklyplan.R;
import viviant.cn.weeklyplan.bean.Planthing;

/**
 * Created by weiwei.huang on 16-3-24.
 */
public class HistroyPlanListViewAdapter extends BaseAdapter {
    private LayoutInflater listContainer;//视图容器
    private Context context;
    private List<Planthing> deletePlanthingList;


    public HistroyPlanListViewAdapter(Context context, List<Planthing> deletePlanthingList) {
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.deletePlanthingList = deletePlanthingList;

    }

    @Override
    public int getCount() {
        return (deletePlanthingList == null ? 0 : deletePlanthingList.size());
    }

    @Override
    public Object getItem(int i) {
        return deletePlanthingList.get(i);
    }

    @Override
    public long getItemId(int i) {
//        return i;
        Log.d("weiwei", "getItemId " + deletePlanthingList.get(i).getId());
        return deletePlanthingList.get(i).getId();


    }

    public class  ViewHolder{ //自定义控件集合
        TextView timeText;
        TextView descText;
        TextView plannameText;
        CircleImageView planState;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("weiwei", "getView--");
        final Planthing planthing = (Planthing)getItem(i);
        ViewHolder  viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            Log.d("weiwei", "getView");
            view = listContainer.inflate(R.layout.histroy_plan_listview_item,null);
            viewHolder.plannameText = (TextView)view.findViewById(R.id.plan_name);
            viewHolder.timeText = (TextView)view.findViewById(R.id.plan_time);
            viewHolder.descText = (TextView)view.findViewById(R.id.plan_desc);
            viewHolder.planState = (CircleImageView)view.findViewById(R.id.plan_state);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.timeText.setText(planthing.getDoDateTime());
        viewHolder.descText.setText(planthing.getPlanthingDescription());
        viewHolder.plannameText.setText(planthing.getPlanthingName());
        return view;
    }


}
