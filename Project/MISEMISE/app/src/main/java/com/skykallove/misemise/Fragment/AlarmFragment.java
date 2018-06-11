package com.skykallove.misemise.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.skykallove.misemise.Activity.MainActivity;
import com.skykallove.misemise.Data.AlarmListViewAdapter;
import com.skykallove.misemise.R;

public class AlarmFragment extends Fragment {

    public AlarmFragment() {
        // Required empty public constructor
    }

    public View view;

    FloatingActionButton addAlarmButton;

    ListView listview;
    AlarmListViewAdapter adapter;

    private static AlarmFragment instance = null;

    public static AlarmFragment getInstance() {
        return (instance == null ? instance = new AlarmFragment() : instance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alarm, container, false);

        adapter = AlarmListViewAdapter.getInstance();
        listview = (ListView) view.findViewById(R.id.alarm_alarm_list);
        listview.setAdapter(adapter);

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
                MainActivity.instance.replaceFragment(AddAlarmFragment.getInstance());
            }
        });
    }

    public void AddListViewItem(String hour, String minute, String memo) {
        Log.i("test", "test1");
        adapter.addItem(hour + ":" + minute, memo);
        Log.i("test", "test2");
        adapter.notifyDataSetChanged();
        Log.i("test", "test3");
    }
}
