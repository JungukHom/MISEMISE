package com.skykallove.misemise.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skykallove.misemise.R;

public class AlarmFragment extends Fragment {

    public AlarmFragment() {
        // Required empty public constructor
    }

    View view;

    FloatingActionButton addAlarmButton;

    private static AlarmFragment instance = null;

    public static  AlarmFragment getInstance() {
        return (instance == null ? instance = new AlarmFragment() : instance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alarm, container, false);

        findUIObjects();

        return view;
    }

    private void findUIObjects() {
        findButtons();
    }

    private void findButtons() {
        addAlarmButton = (FloatingActionButton) view.findViewById(R.id.alarm_addAlarm);
        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018-06-05
            }
        });
    }
}
