package com.up72.library.utils;

import java.util.Calendar;

/**
 * 时间工具
 **/

public class TimeUtils {


    /**
     * 毫秒换成00:00:00  时：分：秒
     *
     * @param milliseconds 毫秒
     *
     * @return 00:00:00 格式字符串
     */

    public static String getCountTimeByLong(long milliseconds) {

        int totalTime = (int) (milliseconds/1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }

        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }

        return sb.toString();

    }

    /**
     * 毫秒换成00:00:00  时：分：秒
     *
     * @param milliseconds 毫秒
     *
     * @return -2:前天,-1：昨天,0：今天,1：明天,2：后天  HH:mm:ss  ， yyyy-MM-dd  HH:mm:ss时间格式字符串
     */

    public static String getTodayOrYesterday(long milliseconds) {

        //所在时区时8，系统初始时间是1970-01-01 80:00:00，注意是从八点开始，计算的时候要加回去
        int offSet = Calendar.getInstance().getTimeZone().getRawOffset();
        long today = (System.currentTimeMillis() + offSet) / 86400000L;
        long start = (milliseconds + offSet) / 86400000L;
        long intervalTime = start - today;

        //-2:前天,-1：昨天,0：今天,1：明天,2：后天
        String strDes = "";
        if (intervalTime == 1) {
            strDes = "明天" + DateUtils.msToString(milliseconds, " HH:mm:ss");//明天
        } else if (intervalTime == 2) {
            strDes = "后天" + DateUtils.msToString(milliseconds, " HH:mm:ss");//后天
        } else if (intervalTime == 0) {
            strDes = "今天" + DateUtils.msToString(milliseconds, " HH:mm:ss");//今天
        } else if (intervalTime == -1) {
            strDes = "昨天" + DateUtils.msToString(milliseconds, " HH:mm:ss");//昨天
        } else if (intervalTime == -2) {
            strDes = "前天" + DateUtils.msToString(milliseconds, " HH:mm:ss");//前天
        } else {
            strDes = DateUtils.msToString(milliseconds, "yyyy-MM-dd  HH:mm:ss");//直接显示时间
        }

        return strDes;
    }
}
