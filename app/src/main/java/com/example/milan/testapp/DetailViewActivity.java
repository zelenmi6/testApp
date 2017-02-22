package com.example.milan.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import json.ShowDetails;

/**
 * Created by Milan on 2/21/2017.
 */

public class DetailViewActivity extends AppCompatActivity{
    private ShowDetails details;

    @BindView(R.id.detail_name) TextView name;
    @BindView(R.id.detail_url) TextView URL;
    @BindView(R.id.detail_type) TextView type;
    @BindView(R.id.detail_language) TextView language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view_layout);
        ButterKnife.bind(this);
        fillFields();
    }

    private void fillFields() {
        details = (ShowDetails)getIntent().getSerializableExtra("SHOW_INFORMATION");
        name.setText(details.getName());
        URL.setText(details.getUrl());
        type.setText(details.getType());
        language.setText(details.getLanguage());
    }

}
