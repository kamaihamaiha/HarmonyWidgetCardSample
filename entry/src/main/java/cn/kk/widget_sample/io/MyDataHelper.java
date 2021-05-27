package cn.kk.widget_sample.io;

import cn.kk.widget_sample.api.SPKey;
import cn.kk.widget_sample.utils.PreferencesHelper;
import ohos.app.Context;

/**
 * 数据存取帮助类
 */
public class MyDataHelper {

    /**
     * 保存卡片 id
     * @param id
     * @param context
     */
    public static void saveCardId(long id, Context context){
        PreferencesHelper.getInstance(context).saveLongValue(SPKey.WIDGET_FORM_ID,id);
    }

    /**
     * 获取卡片 id
     * @param context
     * @return
     */
    public static long getCardId(Context context){
       return PreferencesHelper.getInstance(context).getLongValue(SPKey.WIDGET_FORM_ID);
    }
}
