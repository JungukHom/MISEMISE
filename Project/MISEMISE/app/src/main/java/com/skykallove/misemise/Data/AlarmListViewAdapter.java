package com.skykallove.misemise.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skykallove.misemise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 2018-06-09.
 */

public class AlarmListViewAdapter extends BaseAdapter {

    private List<AlarmListItem> listViewItem = new ArrayList<>();

    private AlarmListViewAdapter() {
    }

    private static AlarmListViewAdapter instance = null;
    public static AlarmListViewAdapter getInstance() {
        return (instance == null ? instance = new AlarmListViewAdapter() : instance);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alarm_listview_item, parent, false);
        }

        TextView time = (TextView) convertView.findViewById(R.id.item_time);

        AlarmListItem alarmListItem = listViewItem.get(position);

        time.setText(alarmListItem.getTime());

        return convertView;
    }

    @Override
    public int getCount() {
        return listViewItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String time) {
        AlarmListItem item = new AlarmListItem();

        item.setTime(time);

        listViewItem.add(item);
    }
}