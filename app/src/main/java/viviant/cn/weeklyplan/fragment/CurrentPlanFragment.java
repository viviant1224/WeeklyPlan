package viviant.cn.weeklyplan.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.alamkanak.weekview.WeekViewEvent;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.drakeet.materialdialog.MaterialDialog;
import viviant.cn.weeklyplan.MainActivity;
import viviant.cn.weeklyplan.PlanInfoActivity;
import viviant.cn.weeklyplan.R;
import viviant.cn.weeklyplan.bean.Level;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.constant.Constants;
import viviant.cn.weeklyplan.db.LevelDBManager;
import viviant.cn.weeklyplan.db.PlanthingDBManager;
import viviant.cn.weeklyplan.service.PlanthingData;
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

    List<WeekViewEvent> events = null;



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
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        events = new ArrayList<WeekViewEvent>();
        if (newMonth == DateUtil.getCurrentMonth()) {
            List<Planthing> planthingList = new PlanthingData().getUnDeletePlanthings();
            for (int i = 0; i< planthingList.size(); i++) {
                Calendar startTime = DateUtil.getCalendar(planthingList.get(i).getDoDateTime());
                Calendar endTime = DateUtil.getCalendar(planthingList.get(i).getEndDateTime());
                WeekViewEvent event = new WeekViewEvent(planthingList.get(i).getId(), getEventTitle(planthingList.get(i)), startTime, endTime);

                if (planthingList.get(i).getState() == 1) {
                    event.setColor(getResources().getColor(R.color.event_color_03));
                } else {
                    switch (Integer.parseInt(planthingList.get(i).getLevelId() + "")) {
                        case 1:
                            event.setColor(getResources().getColor(R.color.event_color_01));
                            break;
                        case 2:
                            event.setColor(getResources().getColor(R.color.event_color_04));
                            break;
                        case 3:
                            event.setColor(getResources().getColor(R.color.event_color_05));
                            break;
                        case 4:
                            event.setColor(getResources().getColor(R.color.event_color_05));
                            break;
                        case 5:
                            event.setColor(getResources().getColor(R.color.colorAccent));
                            break;
                        default:
                            event.setColor(getResources().getColor(R.color.event_color_01));
                            break;
                    }
                }

                events.add(event);
            }
        }
        return events;
    }
    //add by weiwei end

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Planthing mPlanthing = (Planthing)(PlanthingDBManager.getPlanthingDBManager().getObjectById(event.getId() + ""));
        Intent mIntent = new Intent(getContext(), PlanInfoActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(Constants.INTENT_PLAN_THING_UPDATE, mPlanthing);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

        Planthing planthing = new PlanthingData().getPlanthingById(event.getId());
        Level level = (Level)(new LevelDBManager().getObjectById((planthing.getLevelId()+"")));
        String message = "time : " + planthing.getDoDateTime() + "\n" +
                "Level : " + level.getLevelName() + "\n" +
                "planthingdesc : " + planthing.getPlanthingDescription() + "\n" +
                "isRemind" + planthing.getFlagRemind();

        showPlanthingDialog(planthing, message, event);
    }

    private void showPlanthingDialog(final Planthing planthing, String message, final WeekViewEvent event) {

        final MaterialDialog mMaterialDialog = new MaterialDialog(getContext());
        if (planthing.getState() != 1) {
            mMaterialDialog.setPositiveButton("compelet", new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure?")
                            .setContentText("Won't be able to recover this file!")
                            .setConfirmText("yes, compelet it")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    planthing.setState(1);
                                    boolean updateSuccess = new PlanthingData().updatePlanthing(planthing);
                                    if (updateSuccess) {
                                        event.setColor(getResources().getColor(R.color.event_color_02));
                                        getWeekView().notifyDatasetChanged();
                                        sDialog
                                                .setTitleText("update!")
                                                .setContentText("Your plan is updated!")
                                                .setConfirmText("ok")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        sDialog.dismiss();
                                                    }
                                                })
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        mMaterialDialog.dismiss();
                                    } else {
                                        sDialog
                                                .setTitleText("update failed!")
                                                .setContentText("something is wrong!")
                                                .setConfirmText("ok")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        sDialog.dismiss();
                                                    }
                                                })
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        mMaterialDialog.dismiss();
                                    }

                                }
                            }).show();
                }
            });
        }

        mMaterialDialog.setTitle(planthing.getPlanthingName())
                .setMessage(message)
                .setCanceledOnTouchOutside(true)
                .setNegativeButton(getContext().getString(R.string.delete), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(getContext().getString(R.string.confirm_title))
                                .setContentText(getContext().getString(R.string.confirm_content))
                                .setConfirmText(getContext().getString(R.string.confirm_delete))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        planthing.setState(2);
                                        boolean deleteSuccess = new PlanthingData().updatePlanthing(planthing);
//                                        boolean success = new PlanthingData().deletePlanthing(planthing.getId());
                                        if (deleteSuccess) {
                                            events.remove(event);
                                            getWeekView().notifyDatasetChanged();
                                            sDialog
                                                    .setTitleText(getContext().getString(R.string.deleted))
                                                    .setContentText(getContext().getString(R.string.deleted_content))
                                                    .setConfirmText(getContext().getString(R.string.ok))
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sDialog) {
                                                            sDialog.dismiss();
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            mMaterialDialog.dismiss();
                                        } else {
                                            sDialog
                                                    .setTitleText("delete failed!")
                                                    .setContentText("something is wrong!")
                                                    .setConfirmText("ok")
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sDialog) {
                                                            sDialog.dismiss();
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            mMaterialDialog.dismiss();
                                        }

                                    }
                                }).show();
                    }
                });
        mMaterialDialog.show();
    }

    private String getEventTitle (Planthing planthing) {

//        String eventTile = ;
        return String.format(getString(R.string.event_title), planthing.getPlanthingName(), planthing.getPlanthingDescription());

//        return String.format("Plan : %s \n Plan description: %s", planthing.getPlanthingName(), planthing.getPlanthingDescription());
//        return planthing.getPlanthingName();

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
