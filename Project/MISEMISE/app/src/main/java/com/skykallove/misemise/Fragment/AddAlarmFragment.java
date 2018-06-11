package com.skykallove.misemise.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.skykallove.misemise.Activity.MainActivity;
import com.skykallove.misemise.Data.AlarmListViewAdapter;
import com.skykallove.misemise.R;

public class AddAlarmFragment extends Fragment {

    public AddAlarmFragment() {
        // Required empty public constructor
    }

    private static AddAlarmFragment instance = null;

    public static  AddAlarmFragment getInstance() {
        return (instance == null ? instance = new AddAlarmFragment() : instance);
    }

    View view;



    TimePicker timePicker;
    EditText memo;
    Button cancel;
    Button save;

    final MainActivity mainActivity = MainActivity.instance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_alarm, container, false);



        findUIObjects();
        setOnClickListeners();

        return view;
    }

    private void findUIObjects() {
        findButtons();
        findOthers();
    }

    private void findButtons() {
        cancel = (Button) view.findViewById(R.id.add_alarm_cancel);
        save = (Button) view.findViewById(R.id.add_alarm_save);
    }

    private void findOthers() {
        memo = (EditText) view.findViewById(R.id.add_alarm_memo);
        timePicker = (TimePicker) view.findViewById(R.id.add_alarm_time_picker);
    }

    private void setOnClickListeners() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToAlarmFragment();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToAlarmFragment();
                saveAlarmInfo();
            }
        });
    }

    private void goBackToAlarmFragment() {
        mainActivity.replaceFragment(mainActivity.currentFragment, mainActivity.currentFragmentID);
    }

    private void saveAlarmInfo() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        String memo = this.memo.getText().toString();

        // TODO: 2018-06-09 save this infos into db
        // TODO: 2018-06-09 save this infos into list
        Log.i("test", "hour : " + hour);
        Log.i("test", "minute : " + minute);

        String _hour = Integer.toString(hour);
        String _minute = Integer.toString(minute);

        if (_hour.length() == 1) {
            _hour = "0" + _hour;
        }
        if (_minute.length() == 1) {
            _minute = "0" + _minute;
        }

        AlarmFragment.getInstance().AddListViewItem(_hour, _minute, memo);
    }
}
