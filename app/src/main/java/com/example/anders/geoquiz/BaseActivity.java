package com.example.anders.geoquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import butterknife.ButterKnife;

public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getClass().getAnnotation(ContentView.class).value());
        ButterKnife.bind(this);
    }
}
