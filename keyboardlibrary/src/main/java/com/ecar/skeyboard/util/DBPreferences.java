package com.ecar.skeyboard.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ecar.skeyboard.R;

/**
 * 类名称: DBPreferences
 * 类描述: SharedPreferences 存储
 * 创建人: 金征
 * 创建时间: 2015年4月28日 下午2:11:29
 * 修改人: 黄璜
 * 修改时间: 2015年4月28日 下午2:11:29
 * <p>
 * 注：在get方法中加上try catch 是为了防止后期修改数据类型导致数据转换错误
 */
public class DBPreferences {

    private SharedPreferences dbs;

    private Context mContext;

    public static final String SP_CURRENT_VERSION = "version";// 当前版本

    private final String CtUserNumber = "UserNumberPhone";

    private final String CtUserId = "loginUserId";

    private final String CtPassword = "Password";

    private final String CtSound = "sound";

    private final String CtVibrate = "vibrate";

    private final String CtPush = "push";

    private final String CtFirstInstalled = "firstInstalled";// 第一次进入应用

    private final String CtFirstOpendParkDetail = "firstpark";// 第一次打开停车详情页面

    private final String CtFirstShowRegisterGuide = "firstguide";// 第一次显示注册提醒页面

    private final String CtFirstInitCityInfo = "firstInitCityInfo";// 第一次显示注册提醒页面

    public final String SpCurrentLocationCityCode = "current_location_city_code";

    public final String SpCurrentLocationCityName = "current_location_cityname";//当前定位城市

    public final String SpCurrentLocationCityProvince = "current_location_province";//当前定位省份

    public final String SpIsLocationCityValid = "is_location_city_valid";//当前定位城市是否有效

    public final String SpIsLocationFail = "is_location_fail";//当前定位是否失败

    public static final String SpCurrentSelectCityCode = "current_Select_cityseria";

    public static final String SpCurrentSelectCityName = "current_Select_cityname";//当前选择城市

    public static final String SpUnreadMsgCount = "Un_read_Msg_Count";//当前未读消息数

    public static final String SpGiftCoupons = "Un_Gift_Coupon_Count";//注册成功后用户卡券数

    public static final String SpCurrentUserBindCarCount = "Sp_Current_User_Bind_Car_Count";//当前用户绑定车牌数

    public static final String SpPayMethod = "Sp_Pay_Method";//用户上一次支付方式

    public static final String SpLastGetCodeNum = "Sp_Last_Get_Code_Num";//用户上一次获取验证码时的电话号码

    public static final String SpLastOperationVerCodePage = "Sp_Last_Operation_Ver_Code_Page";//用户上一次操作获取验证码时的页面

    public static final String SpIsShowEvaluateDialog = "Sp_Is_Show_Evaluate_Dialog";//是否已经展示评价引导弹窗

    public static final String SpInstallTime = "Sp_Install_Time";//安装应用的时间

    private final String DB_PREFS_NAME = "com.preferences.dbs";

    private static DBPreferences dbPreferences;

    private DBPreferences(Context context) {
        this.mContext = context;
        dbs = context.getSharedPreferences(DB_PREFS_NAME, 0);
    }

    public static DBPreferences getDefault(Context context) {
        if (dbPreferences == null) {
            synchronized (DBPreferences.class) {
                dbPreferences = new DBPreferences(context);
            }
        }
        return dbPreferences;
    }


    public void setVersion(int version) {
        dbs.edit().putInt(SP_CURRENT_VERSION, version).apply();
    }

    /**
     * 登录的用户的电话号码
     */
    public String getUserNumberPhone() {
        try {
            return dbs.getString(CtUserNumber, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setNumberPhone(String aUserNumberPhone) {
        dbs.edit().putString(CtUserNumber, aUserNumberPhone).apply();
    }

    /**
     * 登录用户的密码
     */
    public String getPassword() {
        try {
            return dbs.getString(CtPassword, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setPassword(String aPassword) {
        dbs.edit().putString(CtPassword, aPassword).apply();
    }

    public boolean getSound() {
        return dbs.getBoolean(CtSound, true);
    }

    public void setSound(boolean aSound) {
        dbs.edit().putBoolean(CtSound, aSound).apply();
    }

    public boolean getVibrate() {
        return dbs.getBoolean(CtVibrate, true);
    }

    public void setVibrate(boolean aVibrate) {
        dbs.edit().putBoolean(CtVibrate, aVibrate).apply();
    }

    public boolean getPush() {
        return dbs.getBoolean(CtPush, true);
    }

    public void setPush(boolean aPush) {
        dbs.edit().putBoolean(CtPush, aPush).apply();
    }

    /**
     * 功能：是否第一次进入应用
     */
    public boolean isFirstInstalled() {
        return dbs.getBoolean(CtFirstInstalled, true);
    }

    public void setFirstInstalled() {
        dbs.edit().putBoolean(CtFirstInstalled, false).apply();
    }

    /**
     * 功能：是否第一打开地图详情界面
     */
    public boolean isFirstOpenParkDetail() {
        return dbs.getBoolean(CtFirstOpendParkDetail, true);
    }

    public void setFirstOpenParkDetail() {
        dbs.edit().putBoolean(CtFirstOpendParkDetail, false).apply();
    }

    /**
     * 功能：是否第一次进入应用
     */
    public boolean isFirstShowRegisterGuide() {
        return dbs.getBoolean(CtFirstShowRegisterGuide, true);
    }

    public void setFirstShowRegisterGuide() {
        dbs.edit().putBoolean(CtFirstShowRegisterGuide, false).apply();
    }

    public boolean isFirstInitCityInfo() {
        return dbs.getBoolean(CtFirstInitCityInfo, true);
    }

    public void setFirstInitCityInfo() {
        dbs.edit().putBoolean(CtFirstInitCityInfo, false).apply();
    }

    public String getLoginUserId() {
        try {
            return dbs.getString(CtUserId, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setLoginUserId(String userId) {
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        dbs.edit().putString(CtUserId, userId).apply();
    }

    public void clearLoginUserId() {
        dbs.edit().putString(CtUserId, "").apply();
    }

    public boolean isLocationCityValid() {
        return dbs.getBoolean(SpIsLocationCityValid, false);
    }

    public void setLocationCityValid(boolean valid) {
        dbs.edit().putBoolean(SpIsLocationCityValid, valid).apply();
    }

    public boolean isLocationFail() {
        return dbs.getBoolean(SpIsLocationFail, false);
    }

    public void setLocationFail(boolean locationFail) {
        dbs.edit().putBoolean(SpIsLocationFail, locationFail).apply();
    }

    public String getLocationCityCode() {
        try {
            return dbs.getString(SpCurrentLocationCityCode, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setLocationCityCode(String userId) {
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        dbs.edit().putString(SpCurrentLocationCityCode, userId).apply();
    }

    public String getSelectCityCode() {
        try {
            return dbs.getString(SpCurrentSelectCityCode, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setSelectCityCode(String userId) {
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        dbs.edit().putString(SpCurrentSelectCityCode, userId).apply();
    }

    public String getLocationCityName() {
        try {
            return dbs.getString(SpCurrentLocationCityName, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setLocationCityName(String userId) {
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        dbs.edit().putString(SpCurrentLocationCityName, userId).apply();
    }

    public String getSelectCityName() {
        try {
            return dbs.getString(SpCurrentSelectCityName, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setSelectCityName(String userId) {
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        dbs.edit().putString(SpCurrentSelectCityName, userId).apply();
    }

    public String getGiftCoupons() {
        try {
            return dbs.getString(SpGiftCoupons, "0");
        } catch (Throwable t) {
            return "0";
        }
    }

    public void setGiftCoupons(String count) {
        dbs.edit().putString(SpGiftCoupons, count).apply();
    }

    public String getLocationCityProvince() {
        try {
            return dbs.getString(SpCurrentLocationCityProvince,
                    mContext.getString(R.string.default_province));
        } catch (Throwable e) {
            return mContext.getString(R.string.default_province);
        }
    }

    public void setLocationCityProvince(String userId) {
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        dbs.edit().putString(SpCurrentLocationCityProvince, userId).apply();
    }

    public int getUnreadMsgCount() {
        try {
            return dbs.getInt(SpUnreadMsgCount, 0);
        } catch (Throwable e) {
            return 0;
        }
    }

    public void setUnreadMsgCount(int unreadMsgCount) {
        dbs.edit().putInt(SpUnreadMsgCount, unreadMsgCount).apply();
    }


    /**
     * 最后获取验证码的用户手机号
     */
    public String getLastGetCodeNum() {
        try {
            return dbs.getString(SpLastGetCodeNum, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setLastGetCodeNum(String num) {
        dbs.edit().putString(SpLastGetCodeNum, num).apply();
    }

    /**
     * 最后操作获取验证码的页面
     */
    public int getLastOperationVerCodePage() {
        try {
            return dbs.getInt(SpLastOperationVerCodePage, -1);
        } catch (Throwable e) {
            return -1;
        }
    }

    public void setLastOperationVerCodePage(int num) {
        dbs.edit().putInt(SpLastOperationVerCodePage, num).apply();
    }

    /**
     * 最后操作获取验证码的页面
     */
    public long getInstallTime() {
        try {
            return dbs.getLong(SpLastOperationVerCodePage, System.currentTimeMillis());
        } catch (Throwable e) {
            return System.currentTimeMillis();
        }
    }

    public void setInstallTime(long time) {
        dbs.edit().putLong(SpLastOperationVerCodePage, time).apply();
    }

    /**
     * 功能：是否已经展示评价引导弹窗
     */
    public boolean isShowEvaluateDialog() {
        return dbs.getBoolean(SpIsShowEvaluateDialog, false);
    }

    public void setShowEvaluateDialog(boolean isShow) {
        dbs.edit().putBoolean(SpIsShowEvaluateDialog, isShow).apply();
    }

}
