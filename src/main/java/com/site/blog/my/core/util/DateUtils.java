package com.site.blog.my.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fuchenggang
 */
public class DateUtils {

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    private static final Object object = new Object();

    /**
     * 获取SimpleDateFormat
     * @param pattern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     * @param date 日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }
}
