package cn.kk.widget_sample.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 时间戳转格式化时间
     *
     * @param stamp 单位毫秒
     * @return
     */
    public static String getTime(long stamp) {
        Date date = new Date(stamp);
        //日期格式化
        String res = simpleDateFormat.format(date);

        return res;
    }

    public static String getTime() {
        Date date = new Date(System.currentTimeMillis());
        //日期格式化
        String res = simpleDateFormat.format(date);

        return res;
    }

    public static String getTime(Date date) {
        //日期格式化
        String res = simpleDateFormat.format(date);
        return res;
    }

}
