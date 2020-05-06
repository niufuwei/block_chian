package com.hjq.demo.ui.activity.otc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hjq.demo.R;

import butterknife.ButterKnife;

public class DetailedRecordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_record);
        ButterKnife.bind(this);
    }
}
