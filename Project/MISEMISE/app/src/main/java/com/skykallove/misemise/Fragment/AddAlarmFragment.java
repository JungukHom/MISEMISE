package com.skykallove.misemise.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.skykallove.misemise.Activity.MainActivity;
import com.skykallove.misemise.R;

import java.util.ArrayList;
import java.util.List;

public class AddAlarmFragment extends Fragment {

    public AddAlarmFragment() {
        // Required empty public constructor
    }

    private static AddAlarmFragment instance = null;

    public static AddAlarmFragment getInstance() {
        return (instance == null ? instance = new AddAlarmFragment() : instance);
    }

    View view;

    TimePicker timePicker;

    List<ToggleButton> toggleButtonList = new ArrayList<>();
    ToggleButton monday;
    ToggleButton tuesday;
    ToggleButton wednesday;
    ToggleButton thursday;
    ToggleButton friday;
    ToggleButton saturday;
    ToggleButton sunday;

    Button cancel;
    Button save;

    final MainActivity mainActivity = MainActivity.instance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_alarm, container, false);

        findUIObjects();
        setOnClickListeners();
        addToggleButtonsToList();
        setToggleButtonListeners();

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
        timePicker = (TimePicker) view.findViewById(R.id.add_alarm_time_picker);

        monday = (ToggleButton) view.findViewById(R.id.list_item_monday);
        tuesday = (ToggleButton) view.findViewById(R.id.list_item_tuesday);
        wednesday = (ToggleButton) view.findViewById(R.id.list_item_wednesday);
        thursday = (ToggleButton) view.findViewById(R.id.list_item_thursday);
        friday = (ToggleButton) view.findViewById(R.id.list_item_friday);
        saturday = (ToggleButton) view.findViewById(R.id.list_item_saturday);
        sunday = (ToggleButton) view.findViewById(R.id.list_item_sunday);
    }

    private void addToggleButtonsToList() {
        toggleButtonList.add(monday);
        toggleButtonList.add(tuesday);
        toggleButtonList.add(wednesday);
        toggleButtonList.add(thursday);
        toggleButtonList.add(friday);
        toggleButtonList.add(saturday);
        toggleButtonList.add(sunday);
    }

    private void setToggleButtonListeners() {
        for (int i = 0; i < toggleButtonList.size(); i++) {
            toggleButtonList.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        buttonView.setBackgroundColor(getResources().getColor(R.color.gray));
                    } else {
                        buttonView.setBackgroundColor(getResources().getColor(R.color.noColor));
                    }
                }
            });
        }
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
        mainActivity.replaceFragment(AlarmFragment.getInstance(), R.layout.fragment_alarm);
    }

    private void saveAlarmInfo() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

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

        AlarmFragment.getInstance().addListViewItem(_hour, _minute);
    }
}
