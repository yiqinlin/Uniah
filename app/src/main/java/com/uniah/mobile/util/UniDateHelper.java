package com.uniah.mobile.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UniDateHelper {


    public static String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return formatter.format(new Date());
    }

    public static boolean OutTime(String dateStr){
        if(dateStr!=null&&!dateStr.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            try {
                Date oldDate = formatter.parse(dateStr);
                Calendar now = Calendar.getInstance();
                Calendar old = Calendar.getInstance();
                old.setTime(oldDate);
                return old.compareTo(now) <= 0;
            }catch (Throwable e){
                return true;
            }
        }
        return true;
    }

    public static String SmartLongTime(String dateStr) throws ParseException {
        if(dateStr!=null&&!dateStr.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            SimpleDateFormat outTimeFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
            SimpleDateFormat outDateFormat = new SimpleDateFormat("MM-dd", Locale.CHINA);
            Date oldDate = formatter.parse(dateStr);
            Calendar now = Calendar.getInstance();
            Calendar now1 = Calendar.getInstance();
            Calendar now2 = Calendar.getInstance();
            Calendar old = Calendar.getInstance();
            old.setTime(oldDate);
            String outDate=outDateFormat.format(oldDate);
            String outTime=outTimeFormat.format(oldDate);
            if(now.get(Calendar.YEAR)!=old.get(Calendar.YEAR)){
                return old.get(Calendar.YEAR)+"-"+outDate;
            }
            now1.add(Calendar.DAY_OF_YEAR,-1);
            now2.add(Calendar.DAY_OF_YEAR,-2);
            if(now.get(Calendar.DAY_OF_MONTH)==old.get(Calendar.DAY_OF_MONTH)){
                return outTime;
            }else if(now1.get(Calendar.DAY_OF_MONTH)==old.get(Calendar.DAY_OF_MONTH)){
                return "昨天 " + outTime;
            }else if(now2.get(Calendar.DAY_OF_MONTH)==old.get(Calendar.DAY_OF_MONTH)){
                return "前天 " + outTime;
            }else{
                return outDate + " " + outTime;
            }
        }
        return "";
    }

    public static String SmartTime(String dateStr) throws ParseException {
        if(dateStr!=null&&!dateStr.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            SimpleDateFormat outTimeFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
            SimpleDateFormat outDateFormat = new SimpleDateFormat("MM-dd", Locale.CHINA);
            Date oldDate = formatter.parse(dateStr);
            Calendar now = Calendar.getInstance();
            Calendar now1 = Calendar.getInstance();
            Calendar now2 = Calendar.getInstance();
            Calendar old = Calendar.getInstance();
            old.setTime(oldDate);
            String outDate=outDateFormat.format(oldDate);
            String outTime=outTimeFormat.format(oldDate);
            if(now.get(Calendar.YEAR)!=old.get(Calendar.YEAR)){
                return old.get(Calendar.YEAR)+"-"+outDate;
            }
            now1.add(Calendar.DAY_OF_YEAR,-1);
            now2.add(Calendar.DAY_OF_YEAR,-2);
            if(now.get(Calendar.DAY_OF_MONTH)==old.get(Calendar.DAY_OF_MONTH)){
                return outTime;
            }else if(now1.get(Calendar.DAY_OF_MONTH)==old.get(Calendar.DAY_OF_MONTH)){
                return "昨天";
            }else if(now2.get(Calendar.DAY_OF_MONTH)==old.get(Calendar.DAY_OF_MONTH)){
                return "前天";
            }else{
                return outDate;
            }
        }
        return "";
    }
}
