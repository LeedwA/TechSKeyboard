package com.ecar.ecarskeyboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ecar.mylibrary.KeyboardTouchListener;
import com.ecar.mylibrary.KeyboardUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {
    private RelativeLayout rootView;
    private ScrollView scrollView;
    private EditText normalEd1; //普通键盘
    private EditText specialEd1, specialEd2, specialEd3, specialEd4, specialEd5, specialEd6, specialEd7, specialEd8; //特殊键盘

    private KeyboardUtil keyboardUtil;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BlankFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        initMoveKeyBoard(view);
        return view;

    }

    private void initMoveKeyBoard(View view) {
        rootView = (RelativeLayout) view.findViewById(R.id.root_view);
        scrollView = (ScrollView) view.findViewById(R.id.sv_main);

        normalEd1 = (EditText) view.findViewById(R.id.normal_ed1);

        specialEd1 = (EditText) view.findViewById(R.id.special_ed1);
        specialEd2 = (EditText) view.findViewById(R.id.special_ed2);
        specialEd3 = (EditText) view.findViewById(R.id.special_ed3);
        specialEd4 = (EditText) view.findViewById(R.id.special_ed4);
        specialEd5 = (EditText) view.findViewById(R.id.special_ed5);
        specialEd6 = (EditText) view.findViewById(R.id.special_ed6);
        specialEd7 = (EditText) view.findViewById(R.id.special_ed7);
        specialEd8 = (EditText) view.findViewById(R.id.special_ed8);


        keyboardUtil = new KeyboardUtil(getActivity(), rootView, scrollView)
                .setRandom(true)//设置是否为随机键盘
                .setOtherEdittext(normalEd1) //不需要自定义的的键盘
                .setKeyBoardStateChangeListener(new KeyBoardStateListener())//监听键盘切换
                .setInputOverListener(new inputOverListener()).doneOnclick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "键盘", Toast.LENGTH_SHORT).show();
                    }
                });//监听输入事件


        keyboardUtil.hideKeyboardLayout(); //隐藏键盘

        specialEd1.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM, -1)); // 设置自定义键盘的ontouth事件
        specialEd2.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_FINISH, -1)); // 设置自定义键盘的ontouth事件
        specialEd3.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_POINT, -1)); // 设置自定义键盘的ontouth事件
        specialEd4.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_X, -1)); // 设置自定义键盘的ontouth事件
        specialEd5.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_NEXT, -1)); // 设置自定义键盘的ontouth事件
        specialEd6.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_ABC, -1)); // 设置自定义键盘的ontouth事件
        specialEd7.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_ABC, -1)); // 设置自定义键盘的ontouth事件
        specialEd8.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_SYMBOL, -1)); // 设置自定义键盘的ontouth事件
    }


    //监听键盘切换
    class KeyBoardStateListener implements KeyboardUtil.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {
//            System.out.println("state" + state);
//            System.out.println("editText" + editText.getText().toString());
        }
    }


    //监听输入事件
    class inputOverListener implements KeyboardUtil.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {
//            System.out.println("onclickType" + onclickType);
//            System.out.println("editText" + editText.getText().toString());
        }
    }
}
