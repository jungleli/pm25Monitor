package com.example.t440.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t440.myapplication.domain.PM25;
import com.example.t440.myapplication.service.AirServiceClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by T440 on 2015/12/25.
 */
public class MainListActivity extends Activity {
    private ListView listView;

    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list);
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage(getString(R.string.loading_message));

        Bundle bundle = this.getIntent().getExtras();
        String city = bundle.getString("city");
//        String city = "西安";

        onQueryPM25ByCity(city);
    }

    private void onQueryPM25ByCity(String city) {
        if (!TextUtils.isEmpty(city)) {
            showLoading();
            AirServiceClient.getInstance().requestPM25(city, new Callback<List<PM25>>() {
                @Override
                public void onResponse(Response<List<PM25>> response, Retrofit retrofit) {
                 showSuccessScreen(response);
                }
                @Override
                public void onFailure(Throwable t) {
                    showErrorScreen();
                }
            });
        }
    }

    private void showSuccessScreen(Response<List<PM25>> response) {
        hideLoading();
        if (response != null) {
            Toast.makeText(MainListActivity.this, "Success", Toast.LENGTH_LONG).show();
            getAirInfoList(response.body());
        }
    }

    private void showErrorScreen() {
        hideLoading();
        Toast.makeText(MainListActivity.this, R.string.error_message_query_pm25, Toast.LENGTH_SHORT).show();
    }

    private void showLoading() {
        loadingDialog.show();
    }

    private void hideLoading() {
        loadingDialog.dismiss();
    }


    private void getAirInfoList(List<PM25> data) {
        if (data != null && !data.isEmpty()) {
            List<HashMap<String, Object>> infoList = new ArrayList<HashMap<String,Object>>();
            for(PM25 pm25 : data){
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("position", pm25.getPositionName());
                item.put("quality", pm25.getQuality());
                item.put("value", pm25.getpm25Value());
                infoList.add(item);
            }

            SimpleAdapter adapter = new SimpleAdapter(this, infoList, R.layout.activty_item,
                    new String[]{"position", "quality", "value"}, new int[]{R.id.position, R.id.quality, R.id.value});

            listView.setAdapter(adapter);
        }
    }
}