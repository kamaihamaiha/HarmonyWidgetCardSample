package cn.kk.widget_sample.slice;

import cn.kk.widget_sample.ResourceTable;
import cn.kk.widget_sample.io.MyDataHelper;
import cn.kk.widget_sample.utils.ImageUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.FormException;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentProvider;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        findComponentById(ResourceTable.Id_btnUpdate).setClickedListener(component -> {

            ComponentProvider componentProvider = new ComponentProvider(ResourceTable.Layout_form_image_with_info_service_widget_2_2, getContext());
            componentProvider.setText(ResourceTable.Id_tv_card_detail, "手动更新了");
            componentProvider.setImagePixelMap(ResourceTable.Id_iv_card, ImageUtil.getPixelMap(ResourceTable.Media_bg_android,getContext()));


            long smallWidgetFormId = MyDataHelper.getCardId(getContext());
            if (smallWidgetFormId != -1) {
                try {
                    getAbility().updateForm(smallWidgetFormId, componentProvider);
                } catch (FormException e) {
                    e.printStackTrace();
                }
            }

            try {
                getAbility().updateForm(smallWidgetFormId,componentProvider);
            } catch (FormException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
