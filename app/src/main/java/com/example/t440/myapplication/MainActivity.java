package com.example.t440.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    private EditText cityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = (EditText) findViewById(R.id.edit_view_input);

        findViewById(R.id.button_query_pm25).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuery();
            }
        });
}

    private void onQuery() {
        final String city = cityEditText.getText().toString();
        Intent newActivity = new Intent();
        newActivity.setClass(MainActivity.this, MainListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("city",city);
        newActivity.putExtras(bundle);
        startActivity(newActivity);
    }
}

