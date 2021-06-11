package cn.kk.widget_sample;

import cn.kk.widget_sample.utils.PreferencesHelper;
import ohos.aafwk.ability.AbilityPackage;

public class MyApplication extends AbilityPackage {
    private static MyApplication instance;
    private PreferencesHelper preferencesHelper;
    @Override
    public void onInitialize() {
        super.onInitialize();

        instance = this;
        preferencesHelper = PreferencesHelper.getInstance(getContext());
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
