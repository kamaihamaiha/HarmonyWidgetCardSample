package cn.kk.widget_sample;

import cn.kk.widget_sample.slice.TestAbilitySlice;
import cn.kk.widget_sample.utils.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class TestAbility extends Ability {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(TestAbilitySlice.class.getName());

        if (intent.hasParameter("test")) {
            String para = intent.getStringParam("test");
            LogUtil.info("HM", para);
        }
    }
}
