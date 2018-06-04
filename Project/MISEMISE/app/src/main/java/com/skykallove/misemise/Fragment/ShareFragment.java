package com.skykallove.misemise.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skykallove.misemise.R;

public class ShareFragment extends Fragment {

    public ShareFragment() {
        // Required empty public constructor
    }

    private static ShareFragment instance = null;

    public static  ShareFragment create() {
        return (instance == null ? instance = new ShareFragment() : instance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }
}
