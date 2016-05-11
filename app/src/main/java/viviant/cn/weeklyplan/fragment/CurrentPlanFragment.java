package viviant.cn.weeklyplan.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;



import com.alamkanak.weekview.WeekViewEvent;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import viviant.cn.weeklyplan.R;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.db.PlanthingDBManager;
import viviant.cn.weeklyplan.util.DateUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentPlanFragment.OnCurrentFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentPlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentPlanFragment extends BaseCurrentPlanFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Planthing mPlanthing = null;

    private OnCurrentFragmentInteractionListener mListener;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentPlanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentPlanFragment newInstance(String param1, String param2) {
        CurrentPlanFragment fragment = new CurrentPlanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CurrentPlanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mPlanthing = (Planthing)getActivity().getIntent().getSerializableExtra("mPlanthing");


        if (mPlanthing != null) {
            onMonthChange(2016,5);
        }
    }

    //add by weiwei 0509
    private List<Planthing> getPlanthings() {
        return new PlanthingDBManager().loadAll();
    }
    //addd by weiwei end

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        if (newMonth == DateUtil.getCurrentMonth()) {
            List<Planthing> planthingList = getPlanthings();
            for (int i = 0; i< planthingList.size(); i++) {
                Calendar startTime = DateUtil.getCalendar(planthingList.get(i).getDoDateTime());
                Calendar endTime = DateUtil.getCalendar(planthingList.get(i).getEndDateTime());
                WeekViewEvent event = new WeekViewEvent(planthingList.get(i).getId(), getEventTitle(planthingList.get(i)), startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_01));
                events.add(event);
            }
        }


        return events;
    }
    //add by weiwei end

    private String getEventTitle (Planthing planthing) {
        return String.format("Event of %s %s", planthing.getPlanthingName(), planthing.getDoDateTime());

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCurrentFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCurrentFragmentInteractionListener) activity;
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
    public interface OnCurrentFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onCurrentFragmentInteraction(Uri uri);
    }

}
