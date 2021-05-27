package cn.kk.widget_sample.utils;

import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

/**
 * [轻量级偏好数据库](https://developer.harmonyos.com/cn/docs/documentation/doc-guides/database-preference-overview-0000000000030086)
 * [使用](https://developer.huawei.com/consumer/cn/codelabsPortal/carddetails/HarmonyOS-Preferenced-Database)
 */
public class PreferencesHelper {
    static final String FILE_NAME = "config";
    private final Preferences preferences;
    private static PreferencesHelper instance;

    private PreferencesHelper(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        preferences = databaseHelper.getPreferences(FILE_NAME);
    }

    public static PreferencesHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (PreferencesHelper.class) {
                if (instance == null) {
                    instance = new PreferencesHelper(context);
                }
            }
        }
        return instance;
    }

    public int getIntValue(String key) {
        return preferences.getInt(key, -1);
    }

    public long getLongValue(String key) {
        return preferences.getLong(key, -1);
    }

    public String getStringValue(String key) {
        return preferences.getString(key, "");
    }


    /**
     * 异步保存
     *
     * @param key
     * @param value
     */
    public void saveIntValue(String key, int value) {
        preferences.putInt(key, value);
        preferences.flush();
    }

    public void saveLongValue(String key, long value) {
        preferences.putLong(key, value);
        preferences.flush();
    }

    /**
     * 同步保存
     *
     * @param key
     * @param value
     */
    public void saveIntValueSync(String key, int value) {
        preferences.putInt(key, value);
        preferences.flushSync();
    }

    /**
     * 异步保存
     *
     * @param key
     * @param value
     */
    public void saveStringValue(String key, String value) {
        preferences.putString(key, value);
        preferences.flush();
    }

    /**
     * 同步保存
     *
     * @param key
     * @param value
     */
    public void saveStringValueSync(String key, String value) {
        preferences.putString(key, value);
        preferences.flushSync();
    }

    public void deleteValue(String key) {
        preferences.delete(key);
    }

}
