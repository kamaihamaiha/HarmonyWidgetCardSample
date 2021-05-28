package cn.kk.widget_sample;

import cn.kk.widget_sample.io.MyDataHelper;
import cn.kk.widget_sample.slice.MainAbilitySlice;
import cn.kk.widget_sample.utils.ImageUtil;
import cn.kk.widget_sample.utils.TimeUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.ProviderFormInfo;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ComponentProvider;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;

/**
 *
 * 1. 创建卡片
 * 2. 被动更新卡片
 * 3. 删除卡片
 */
public class MainAbility extends Ability {
    private static final int DEFAULT_DIMENSION_2X2 = 2;
    // 这个是在创建卡片时，对话框中输入的，在创建之后 config.json 里面 forms -> name
    private static final String CARD_NAME = "service_widget";
    // 是否有数据
    private boolean hasData = true;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
    }

    @Override
    protected ProviderFormInfo onCreateForm(Intent intent) {
        // todo 卡片使用方拉起卡片时，调用
        String formName = intent.getStringParam(AbilitySlice.PARAM_FORM_NAME_KEY);
        int dimension = intent.getIntParam(AbilitySlice.PARAM_FORM_DIMENSION_KEY, DEFAULT_DIMENSION_2X2);

        // 获取服务卡片 ID，这里获取的是 int 类型。long 类型也可以(华为技术支持说的)
        int formId = intent.getIntParam(AbilitySlice.PARAM_FORM_ID_KEY, -1);

        if (formName.equals(CARD_NAME)) {
            // 2 x 2 卡片
            if (dimension == DEFAULT_DIMENSION_2X2) {

                // 保存卡片 id
                MyDataHelper.saveCardId(formId,getContext());

                // Layout_form_image_with_info_service_widget_2_2 是服务卡片的布局
                ProviderFormInfo providerFormInfo = new ProviderFormInfo(ResourceTable.Layout_form_image_with_info_service_widget_2_2, this);

                // 如果创建的时候，就有数据要放到卡片上，那么调用如下代码
                if (hasData) {
                    ComponentProvider provider = new ComponentProvider(ResourceTable.Layout_form_image_with_info_service_widget_2_2,getContext());

                    // 文字描述
                    provider.setText(ResourceTable.Id_tv_card_detail,"快来一起学鸿蒙吧!");
                    // 更新图片

                    // 点击控制事件


                    // 填充数据到卡片
                    providerFormInfo.mergeActions(provider);
                }

                return providerFormInfo;
            }
        }
        return null;
    }

    @Override
    protected void onUpdateForm(long formId) {
        super.onUpdateForm(formId);

        // todo 被动更新卡片(单位 30 分钟，默认是 1*30 分钟的频率，如果要设置，那么在 config.json 中（forms -> updateDuration）设置，只能是自然数)
        // [Java 卡片开发指导](https://developer.harmonyos.com/cn/docs/documentation/doc-guides/ability-service-widget-provider-java-0000001104082220)

        ComponentProvider componentProvider = new ComponentProvider(ResourceTable.Layout_form_image_with_info_service_widget_2_2, getContext());
        componentProvider.setImagePixelMap(ResourceTable.Id_iv_card, ImageUtil.getPixelMap(ResourceTable.Media_bg_android,getContext()));
        componentProvider.setText(ResourceTable.Id_tv_card_detail, "被动更新了，" + TimeUtil.getTime());

    }

    @Override
    protected void onDeleteForm(long formId) {
        super.onDeleteForm(formId);

        // todo 删除卡片
        if (MyDataHelper.getCardId(getContext()) == formId) {
            MyDataHelper.deleteCardId(getContext());
        }
    }
}
