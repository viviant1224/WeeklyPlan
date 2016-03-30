package viviant.cn.weeklyplan.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import viviant.cn.weeklyplan.R;

/**
 * Created by weiwei.huang on 16-3-30.
 */
public class ListFooterViewWeekPlan extends LinearLayout implements View.OnClickListener{

    protected View mLoading;
    protected View mLoadMore;
    private Context mContext;

    public ListFooterViewWeekPlan(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.load_more:
                mLoadMore.setVisibility(View.GONE);
                mLoading.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mLoading = findViewById(R.id.loading);
        mLoadMore = findViewById(R.id.load_more);
        mLoadMore.setVisibility(View.VISIBLE);

        mLoadMore.setOnClickListener(this);
    }


}
