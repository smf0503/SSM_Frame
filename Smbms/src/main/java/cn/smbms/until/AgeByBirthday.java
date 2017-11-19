package cn.smbms.until;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smf on 2017/7/14.
 */
public class AgeByBirthday {
    public static int getagee(String date1) throws Exception {
        int age = 0;
        if (!("".equals(date1))) {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            long leapYear = (long) getLY(date1);
            Date birthDate = myFormat.parse(date1);
            Date nowdate = new Date();
            Date Date = myFormat.parse(myFormat.format(nowdate));
            age = (int) (((Date.getTime() - birthDate.getTime())
                    / (24 * 60 * 60 * 1000) - leapYear) / 365);
        }
        return age;
    }
    // 计算期间闰年个数
    public static int getLY(String data) {
        int leapYear = 0;
        if (!("".equals(data))) {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int birthYear = Integer.parseInt(data.substring(0, 4)); // 获取出生日期，解析为Date类型
            int currYear = Integer.parseInt(myFormat.format(date)); // 获取当前日期
            for (int year = birthYear; year <= currYear; year++) {
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    leapYear++; // 从出生年到当前年，只有是闰年就+1
                }
            }
        }
        return leapYear;
    }
}
