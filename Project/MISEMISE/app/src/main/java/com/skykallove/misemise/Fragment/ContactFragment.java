package com.skykallove.misemise.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skykallove.misemise.R;

public class ContactFragment extends Fragment {

    public ContactFragment() {
        // Required empty public constructor
    }

    private static ContactFragment instance = null;

    public static  ContactFragment getInstance() {
        return (instance == null ? instance = new ContactFragment() : instance);
    }

    Button sendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        sendButton = (Button) view.findViewById(R.id.contact_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });


        return view;
    }

    public void sendEmail() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        String[] address = {"developer0223@gmail.com"};
        email.putExtra(Intent.EXTRA_EMAIL, address);
        email.putExtra(Intent.EXTRA_SUBJECT,"MISEMISE 문의");
        email.putExtra(Intent.EXTRA_TEXT,"여기에 입력해 주세요.\n");
        startActivity(email);
    }
}