package com.ecar.ecarskeyboard.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;

import com.ecar.ecarskeyboard.R;
import com.ecar.skeyboard.commonkeyboard.CommonKeyboardUtil;
import com.ecar.skeyboard.commonkeyboard.view.CarKeyboardView;
import com.ecar.skeyboard.commonkeyboard.view.GroupCarNumView;

/*************************************
 功能：  app的车牌键盘
 创建者： kim_tony
 创建日期：2017/5/4
 版权所有：深圳市亿车科技有限公司
 *************************************/

public class AppKeyboardActivity extends Activity {

    private GroupCarNumView mGroupCarNumView;
    private CommonKeyboardUtil commonKeyboardUtil;
    private boolean isNew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_commonkeyboard);
        mGroupCarNumView = (GroupCarNumView) findViewById(R.id.group_car_num);
        commonKeyboardUtil = new CommonKeyboardUtil(this, mGroupCarNumView,
                new CarKeyboardView.OnTextListener() {

                    @Override
                    public void onText(String text) {
                        mGroupCarNumView.setContent(text);

                    }

                    @Override
                    public void onSure() {
                        commonKeyboardUtil.hidePopcity();

                    }

                    @Override
                    public void onDelete() {
                        mGroupCarNumView.delete();
                    }
                },true);
        final TextView tv_plate = (TextView) findViewById(R.id.tv_plate);
        TextView tv_change = (TextView) findViewById(R.id.tv_change);
        tv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonKeyboardUtil.changeToNew(isNew=!isNew);
            }
        });
        tv_plate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_plate.setText(commonKeyboardUtil.getCarnum());
            }
        });

    }
}
