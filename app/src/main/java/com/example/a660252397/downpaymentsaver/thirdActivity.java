package com.example.a660252397.downpaymentsaver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class thirdActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //retrieve bundle to bring passed information from first intent
        Bundle data = getIntent().getExtras();
        if(data == null){
            return;
        }



    }
}

