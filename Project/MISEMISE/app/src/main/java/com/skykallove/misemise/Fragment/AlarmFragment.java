package com.skykallove.misemise.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skykallove.misemise.Activity.MainActivity;
import com.skykallove.misemise.Data.AlarmListViewAdapter;
import com.skykallove.misemise.R;

import java.util.List;

public class AlarmFragment extends Fragment {

    public AlarmFragment() {
        // Required empty public constructor
    }

    public View view;

    FloatingActionButton addAlarmButton;

    ListView listview;
    AlarmListViewAdapter adapter;

    private static AlarmFragment instance = null;
    private static boolean isFirst = true;

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

        if (isFirst) {
            List<String> alarmTimes = MainActivity.instance.getAlarmTime();
            for (int i = 0; i < alarmTimes.size(); i++) {
                addListViewItem(alarmTimes.get(i));
            }
            isFirst = false;
        }

        findUIObjects();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listview.setItemsCanFocus(true);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView time = (TextView) view.findViewById(R.id.item_time);

                Toast.makeText(getContext(), "time : " + time.getText(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("test", "aaaa");
                TextView time = (TextView) view.findViewById(R.id.item_time);

                Toast.makeText(getContext(), "time : " + time.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findUIObjects() {
        findButtons();
    }

    private void findButtons() {
        addAlarmButton = (FloatingActionButton) view.findViewById(R.id.alarm_addAlarm);
        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018-06-05 저장하기
                MainActivity.instance.replaceFragment(AddAlarmFragment.getInstance(), R.layout.fragment_alarm);
            }
        });
    }

    public void addListViewItem(String time) {
        MainActivity.instance.addAlarmTime(time);
        adapter.addItem(time);
        adapter.notifyDataSetChanged();
    }

    public void addListViewItem(String hour, String minute) {
        String time = hour + ":" + minute;
        MainActivity.instance.addAlarmTime(time);
        adapter.addItem(time);
        adapter.notifyDataSetChanged();
    }
}
