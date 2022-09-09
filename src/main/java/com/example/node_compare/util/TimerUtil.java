package com.example.node_compare.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Spark
 * @CreateTime: 2022-09-09
 * @Description: TODO
 */
public class TimerUtil {
    public static void main(String[] args) {
        String log = "INFO [09-08|21:21:24.356] AddRemotes";
        String str = log.substring(6, 24);
        System.out.println(parseDate(str));
    }

    public static Date parseDate(String time) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss.SSS");
            Date date = simpleDateFormat.parse("2022-" + time);
            return date;
        }catch (Exception e){}
        return null;
    }
}
