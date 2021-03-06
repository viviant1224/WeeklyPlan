package viviant.cn.weeklyplan.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import viviant.cn.weeklyplan.R;
import viviant.cn.weeklyplan.adapter.HistroyPlanListViewAdapter;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.fragment.dummy.DummyContent;
import viviant.cn.weeklyplan.model.WeekPlan;
import viviant.cn.weeklyplan.service.PlanthingData;
import viviant.cn.weeklyplan.ui.ListFooterViewWeekPlan;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class HistoryPlanFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListFooterViewWeekPlan histroyPlanListFootView;


    /**
     * The fragment's ListView/GridView.
     */
    private SwipeMenuListView swipeMenuListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private HistroyPlanListViewAdapter mAdapter;

    private List<WeekPlan> listItems = null;

    private List<Planthing> deletePlanthingList = null;

    //volley test
    private static final String URL = "http://www.baidu.com/";
    private RequestQueue mQueue; // volley的请求队列
    //volley test end

    // TODO: Rename and change types of parameters
    public static HistoryPlanFragment newInstance(String param1, String param2) {
        HistoryPlanFragment fragment = new HistoryPlanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HistoryPlanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mQueue = Volley.newRequestQueue(this.getContext());//volley test
    }


    /**
     * 创建一个请求，这里我们做一个最简单的通过GET方式请求网页源码的操作。请求成功后打印结果。
     */
    private void volleyRequest() {
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                Toast.makeText(getActivity(), arg0, Toast.LENGTH_LONG).show();
                Log.d("weiwei", arg0);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Toast.makeText(getActivity(), arg0.toString(), Toast.LENGTH_LONG).show();
                Log.d("weiwei", arg0.toString());
            }
        });
        mQueue.add(request);
    }


    private List<Planthing> getDeletePlanthingList() {
        deletePlanthingList = new PlanthingData().getDeletePlanthings();
        return deletePlanthingList;
    }


    private List<WeekPlan> getListItems() {

        listItems = new ArrayList<WeekPlan>();
        for(int i = 0; i < 20; i++) {

            listItems.add(new WeekPlan("time" + i, "desc" + i));
        }
        return listItems;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.swipe_listview);

        showSwipeListView();
        histroyPlanListFootView = (ListFooterViewWeekPlan)inflater.inflate(R.layout.histroyplan_list_foot_view, null);
        swipeMenuListView.addFooterView(histroyPlanListFootView);
        // TODO: Change Adapter to display your content
//        List<WeekPlan> listItems  = getListItems();

        deletePlanthingList = getDeletePlanthingList();
        Log.d("weiwei","---" + deletePlanthingList.size());
        mAdapter = new HistroyPlanListViewAdapter(this.getActivity(),deletePlanthingList);
        swipeMenuListView.setAdapter(mAdapter);

        swipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"onItemclick",Toast.LENGTH_LONG).show();
                volleyRequest();
            }
        });




        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipeView.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_dark), getResources().getColor(android.R.color.holo_blue_light), getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_green_light));
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                Log.d("Swipe", "Refreshing Number");
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
//                        double f = Math.random();
//                        rndNum.setText(String.valueOf(f));
                        mAdapter = new HistroyPlanListViewAdapter(getContext(),getDeletePlanthingList());
                        swipeMenuListView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });

        return view;
    }


    private List<WeekPlan> getLastHistoryPlanItem() {

        listItems = getListItems();
        for(int i = 0; i < 5; i++) {

            listItems.add(new WeekPlan("$" + i, "%" + i));
        }
        return listItems;
    }


    private void showSwipeListView() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.drawable.side_nav_bar);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        swipeMenuListView.setMenuCreator(creator);

        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Toast.makeText(getContext(),"open",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        // delete
                        Toast.makeText(getContext(),"delete",Toast.LENGTH_LONG).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        // Right
//        swipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        // Left
        swipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = swipeMenuListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
