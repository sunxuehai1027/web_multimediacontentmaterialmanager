package Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 江振航的日期工具类，不是很强，但是很实用，安排！！！！！！！！
 */
public class DateUtil {

    static SimpleDateFormat format;
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DF_YYYY_MM_DD_HH_MM_SS_DESC = "HH:mm:ss dd-MM-yyyy";
    public static final String DF_YYYY_MM_DD_HH_MM_SS_WAV = "yyyyMMddHHmmss";
    public static final String DF_YYYY_MM_DD_HH_MM_SS_SHORT = "yyyy/MM/dd HH:mm:ss";
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DF_YYYY_MM_DD_HH_MM_SHORT = "yyyy/MM/dd HH:mm";
    public static final String DF_YYYY_MM_DD_HH_MM_1 = "yyyyMMddHHmmss";
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DF_HH_MM_SS = "HH:mm:ss";
    public static final String DF_HH_MM = "HH:mm";

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年


    /*********************************获取固定格式的字符串类型日期**********************************/

    public static String getCurrentDateStr() {
        format = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
        return format.format(Calendar.getInstance().getTime());
    }

    /*获取自定义格式的当前时间*/
    public static String getCurrentDateStr(String str) {
        format = new SimpleDateFormat(str);
        return format.format(Calendar.getInstance().getTime());
    }

    public static String getMyTime() {
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"//从0计算
                + calendar.get(Calendar.DAY_OF_MONTH) + "日";
        return created;
    }

    /*********************************获取固定时间的Date类型日期**********************************/

    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /*********************************Date类型日期转固定格式String****************************************/

    public static String DateToStr(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String str = format.format(date);
        return str;
    }

    /*********************************String类型日期转固定格式Date****************************************/

    public static Date StrToDate(String str, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*******************************************比较两个日期的大小********************************************/

    public static int compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                // System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                // System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /*******************************************两个日期的前进或者后移一天********************************************/
    public static String moveDate(boolean isNext, String date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DF_YYYY_MM_DD);
        Date d = null;
        try {
            d = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DATE, isNext ? 1 : -1);
        return simpleDateFormat.format(calendar.getTime());
    }

    /************************将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚**********************/
    public static String formatFriendly(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    public static String devideTime() {
        long time1 = System.currentTimeMillis();
        Date date = new Date(time1);
        String time = new SimpleDateFormat("HH:mm:ss").format(date);
        String str = "";
        int hours = Integer.parseInt(time.substring(0, 2));
        if (hours <= 5 && hours >= 1) {
            str = "凌晨好";
        } else if (hours > 5 && hours <= 8) {
            str = "早上好";
        } else if (hours <= 11 && hours > 8) {
            str = "上午好";
        } else if (hours < 13 && hours > 11) {
            str = "中午好";
        } else if (hours <= 17 && hours >= 13) {
            str = "下午好";
        } else if (hours <= 24 && hours > 17) {
            str = "晚上好";
        } else {
            str = "错误";
        }
        return str;
    }

    /**
     * @return long型时间
     */
    public static long getCurrentTimeLong() {
        Date date = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        try {
            date = inputFormat.parse(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static int isSixOrDay() {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String bDate = getCurrentDateStr(DF_YYYY_MM_DD);
        Date bdate = null;
        try {
            bdate = format1.parse(bDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return 1;
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return 2;
        } else {
            return 0;
        }
    }

    public static String getHoutMin() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        return new SimpleDateFormat("HH:mm").format(date);
    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @param start
     * @param end
     * @return
     */
    public static boolean isEffectiveDate(String start, String end) {
        SimpleDateFormat sf = new SimpleDateFormat(DF_HH_MM_SS);
        String now = sf.format(new Date());
        now = getCurrentDateStr(DF_HH_MM_SS);
        Date nowTime;
        try {
            nowTime = new SimpleDateFormat(DF_HH_MM_SS).parse(now);
            Date startTime = new SimpleDateFormat(DF_HH_MM_SS).parse(start);//"09:00:00"
            Date endTime = new SimpleDateFormat(DF_HH_MM_SS).parse(end);//"17:00:00"
            if (isEffectiveDate(nowTime, startTime, endTime)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

   /* public static String delete_(String str){
        String s=null;
        if(str==null) {
            return s;
        }
        s=s.replace("-")
    }*/

}
