package com.skykallove.misemise.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skykallove.misemise.R;

public class WeFragment extends Fragment {

    public WeFragment() {
        // Required empty public constructor
    }

    private static WeFragment instance = null;

    public static  WeFragment getInstance() {
        return (instance == null ? instance = new WeFragment() : instance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_we, container, false);
    }
}