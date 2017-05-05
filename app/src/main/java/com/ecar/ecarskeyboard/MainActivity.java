package com.ecar.ecarskeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ecar.ecarskeyboard.Activitys.AppKeyboardActivity;
import com.ecar.ecarskeyboard.Activitys.PdaKeyboardActivity;
import com.ecar.ecarskeyboard.Activitys.RandomActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rela);

    }


    //随机键盘
    public void random(View v) {
        startActivity(new Intent(this, RandomActivity.class));
    }

    //pda键盘
    public void pdakey(View v) {
        startActivity(new Intent(this, PdaKeyboardActivity.class));
    }

    //app车牌键盘
    public void appkey(View v) {
        startActivity(new Intent(this, AppKeyboardActivity.class));
    }


}