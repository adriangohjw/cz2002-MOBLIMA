/*package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class randomTest {
    public static void main(String[] args) {
        System.out.println(getDayOfWeek("2019-11-14"));
        System.out.println(getTime("2019-11-14 19:22"));
        System.out.println(isWeekend("2019-11-14 19:22"));
        System.out.println(isWeekend("2019-11-15 19:22"));
        System.out.println(isWeekend("2019-11-16 09:22"));
    }

    static boolean isWeekend(String date){
        String dayOfWeek = getDayOfWeek(date);
        if (dayOfWeek.equals("Sat") || dayOfWeek.equals("Sun"))
            return true;
        else if (dayOfWeek.equals("Fri") && getTime(date).isBefore(LocalTime.of(18, 0)))
            return true;
        else
            return false;
    }

    static String getDayOfWeek(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("E");
        String dayOfWeek_Str = null;
        try {
            dayOfWeek_Str = outputFormat.format(inputFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfWeek_Str;
    }

    static LocalTime getTime(String datetime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat newFormat = new SimpleDateFormat("hh:mm");
        String hourMin = null;
        try {
            hourMin = newFormat.format(inputFormat.parse(datetime));
            LocalTime returnData = LocalTime.parse(hourMin);
        return returnData;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}*/
