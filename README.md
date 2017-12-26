# 震动功能
 ##  普通车牌键盘
```
    //  isVibrate是否震动
    public CommonKeyboardUtil(Activity activity, GroupCarNumView mGroupCarNumView, CarKeyboardView.OnTextListener onTextClicked, boolean isVibrate) {
        this.context = activity;
        this.mGroupCarNumView = mGroupCarNumView;
        this.onTextClicked = onTextClicked;
        initData();
        this.isVibrate = isVibrate;

    }
 ```
 ##  PDA车牌键盘
 ###   城市：
```
    例子：
      PdaKeyboardCityUtil pdaKeyboardCityUtil = new PdaKeyboardCityUtil(this, normal_ed0, new CityPopupWindow.OnSelectCity() {
            @Override
            public void getCityFromClick(String city) {
                normal_ed0.setText(TextUtils.isEmpty(city) ? "无" : city);
            }
        }, new CityPopupWindow.OnClicked() {
            @Override
            public void clicked() {
                pdaKeyboardUtil.hideKeyboard();
            }
        }).setVibrate(true);
```

 ###   非城市：
```
    例子：
     pdaKeyboardUtil = new PdaKeyboardNumUtil(keyboardView, this, normal_ed1, new PdaKeyboardNumUtil.SlideView() {
            @Override
            public void hideView() {

            }
        }).setUpper(true).setVibrate(true);//转为大写
```
