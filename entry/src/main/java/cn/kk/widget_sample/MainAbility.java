package cn.kk.widget_sample;

import cn.kk.widget_sample.io.MyDataHelper;
import cn.kk.widget_sample.slice.MainAbilitySlice;
import cn.kk.widget_sample.utils.ImageUtil;
import cn.kk.widget_sample.utils.LogUtil;
import cn.kk.widget_sample.utils.TimeUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.ProviderFormInfo;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ComponentProvider;
import ohos.event.intentagent.IntentAgent;
import ohos.event.intentagent.IntentAgentConstant;
import ohos.event.intentagent.IntentAgentHelper;
import ohos.event.intentagent.IntentAgentInfo;

import java.util.*;

/**
 * 1. 创建卡片
 * 2. 被动更新卡片
 * 3. 删除卡片
 */
public class MainAbility extends Ability {

    private static final int DIMENSION_1X2 = 1;
    private static final int DEFAULT_DIMENSION_2X2 = 2;
    private static final int DEFAULT_DIMENSION_4X4 = 4;

    // 这个是在创建卡片时，对话框中输入的，在创建之后 config.json 里面 forms -> name
    private static final String CARD_NAME = "service_widget";

    // 是否有数据
    private final boolean hasData = true;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());

        // 如果杀死进程再进入是可以获取的。
        if (intent.hasParameter(AbilitySlice.PARAM_FORM_IDENTITY_KEY)) {
            long formId = intent.getLongParam(AbilitySlice.PARAM_FORM_IDENTITY_KEY, 0);
            LogUtil.info("HM", "MainAbility.formId" + formId);
        }
    }

    @Override
    protected ProviderFormInfo onCreateForm(Intent intent) {
        // todo 卡片使用方拉起卡片时，调用
        String formName = intent.getStringParam(AbilitySlice.PARAM_FORM_NAME_KEY);
        int dimension = intent.getIntParam(AbilitySlice.PARAM_FORM_DIMENSION_KEY, DEFAULT_DIMENSION_2X2);

        // 获取服务卡片 ID，这里获取的是 int 类型。long 类型也可以(华为技术支持说的)
        int formId = intent.getIntParam(AbilitySlice.PARAM_FORM_ID_KEY, -1);
//        int R = intent.getIntParam(AbilitySlice.PARAM_FORM_CUSTOMIZE_KEY, -1);
//        int R = intent.getIntParam(AbilitySlice.PARAM_FORM_DIMENSION_KEY, -1);
//        int R = intent.getIntParam(AbilitySlice.PARAM_FORM_HEIGHT_KEY, -1);
//        int R = intent.getIntParam(AbilitySlice.PARAM_FORM_IDENTITY_KEY, -1);
        LogUtil.info("HM", "onCreateForm.dimension=>" + dimension);

        if (formName.equals(CARD_NAME)) {
            // 2 x 2 卡片
            if (dimension == DEFAULT_DIMENSION_2X2) {

                // 保存卡片 id
//                MyDataHelper.saveCardId(formId, getContext());

                // Layout_form_image_with_info_service_widget_2_2 是服务卡片的布局
                ProviderFormInfo providerFormInfo = new ProviderFormInfo(ResourceTable.Layout_form_image_with_info_service_widget_2_2, this);

                // 如果创建的时候，就有数据要放到卡片上，那么调用如下代码
                if (hasData) {
                    ComponentProvider provider = new ComponentProvider(ResourceTable.Layout_form_image_with_info_service_widget_2_2, this);
                    // 文字描述
                    provider.setText(ResourceTable.Id_tv_card_detail, "快来一起学鸿蒙吧!");
                    // 官网文档，照抄点击控制事件
                    provider.setIntentAgent(ResourceTable.Id_tv_card_detail, startAbilityIntentAgent());
                    // 填充数据到卡片
                    providerFormInfo.mergeActions(provider);
                }
                return providerFormInfo;
            }
        }

        // 1*2
        if (formName.equals("qury_form_layout_1_2_a")) {
            if (dimension == DIMENSION_1X2) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_1_2_a, this);
            }
        }
        if (formName.equals("qury_form_layout_1_2_b")) {
            if (dimension == DEFAULT_DIMENSION_2X2) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_1_2_b, this);
            }
        }

        // 2*2
        if (formName.equals("qury_form_layout_2_2_a")) {
            if (dimension == DEFAULT_DIMENSION_2X2) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_2_2_a, this);
            }
        }
        if (formName.equals("qury_form_layout_2_2_b")) {
            if (dimension == DEFAULT_DIMENSION_2X2) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_2_2_b, this);
            }
        }
        if (formName.equals("qury_form_layout_2_2_c")) {
            if (dimension == DEFAULT_DIMENSION_2X2) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_2_2_c, this);
            }
        }
        if (formName.equals("qury_form_layout_2_2_d")) {
            if (dimension == DEFAULT_DIMENSION_2X2) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_2_2_d, this);
            }
        }

        // 2*4
        if (formName.equals("qury_form_layout_2_4_a")) {
            if (dimension == DEFAULT_DIMENSION_4X4) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_2_4_a, this);
            }
        }
        if (formName.equals("qury_form_layout_2_4_b")) {
            if (dimension == DEFAULT_DIMENSION_4X4) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_2_4_b, this);
            }
        }
        if (formName.equals("qury_form_layout_2_4_c")) {
            if (dimension == DEFAULT_DIMENSION_4X4) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_2_4_c, this);
            }
        }
        if (formName.equals("qury_form_layout_2_4_d")) {
            if (dimension == DEFAULT_DIMENSION_4X4) {
                return new ProviderFormInfo(ResourceTable.Layout_qury_form_layout_2_4_d, this);
            }
        }

        return null;
    }

    // 设置触发的事件为系统预置的HarmonyOS betaApp应用
    private IntentAgent startAbilityIntentAgent() {
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                // 除了以下这2个，其余的方法都试过了，均跳不了，不知道怎么回事。
                .withBundleName("cn.kk.widget_sample")
                .withAbilityName("cn.kk.widget_sample.TestAbility")
                .build();
        intent.setOperation(operation);
        intent.setParam("test", "RRR");
        List<Intent> intentList = new ArrayList<>();
        intentList.add(intent);
        List<IntentAgentConstant.Flags> flags = new ArrayList<>();
        flags.add(IntentAgentConstant.Flags.UPDATE_PRESENT_FLAG);
        IntentAgentInfo paramsInfo = new IntentAgentInfo(200, IntentAgentConstant.OperationType.START_ABILITY, flags, intentList, null);
        return IntentAgentHelper.getIntentAgent(this, paramsInfo);
    }

    @Override
    protected void onCastTempForm(long formId) {
        // 使用方将临时卡片转换为常态卡片触发，提供方需要做相应的处理
        super.onCastTempForm(formId);

        LogUtil.info("HM", "onCastTempForm");
    }

    @Override
    protected void onUpdateForm(long formId) {
        super.onUpdateForm(formId);

        // todo 被动更新卡片(单位 30 分钟，默认是 1*30 分钟的频率，如果要设置，那么在 config.json 中（forms -> updateDuration）设置，只能是自然数)
        // [Java 卡片开发指导](https://developer.harmonyos.com/cn/docs/documentation/doc-guides/ability-service-widget-provider-java-0000001104082220)

        ComponentProvider componentProvider = new ComponentProvider(ResourceTable.Layout_form_image_with_info_service_widget_2_2, getContext());
        componentProvider.setImagePixelMap(ResourceTable.Id_iv_card, ImageUtil.getPixelMap(ResourceTable.Media_bg_android, getContext()));
        componentProvider.setText(ResourceTable.Id_tv_card_detail, "被动更新了，" + TimeUtil.getTime());
    }

    @Override
    protected void onDeleteForm(long formId) {
        super.onDeleteForm(formId);

        //  删除卡片
        if (MyDataHelper.getCardId(getContext()) == formId) {
            MyDataHelper.deleteCardId(getContext());
        }
    }

    @Override
    protected void onEventNotify(Map<Long, Integer> formEvents) {
        super.onEventNotify(formEvents);

        LogUtil.info("HM", "onEventNotify," + formEvents);
    }

    public void onForeground(Intent intent) {
        // onResume
        LogUtil.info("HM", "onForeground");
    }

    public void onActive() {
        // 在 onForeground 之后
        LogUtil.info("HM", "onActive");
    }

    public void onInactive() {
        // onPause
        LogUtil.info("HM", "onInactive");
    }

    public void onBackground() {
        // 在 onInactive 之后
        LogUtil.info("HM", "onBackground");
    }

    public void onStop() {
        LogUtil.info("HM", "onStop");
    }

}
