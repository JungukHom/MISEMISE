package com.skykallove.misemise.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skykallove.misemise.R;

public class WHOFragment extends Fragment {

    public WHOFragment() {
        // Required empty public constructor
    }

    private static WHOFragment instance = null;

    public static  WHOFragment create() {
        return (instance == null ? instance = new WHOFragment() : instance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_who, container, false);
    }
}
