package top.javahai.confucius.frame.common.helper;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;


/**
 * @author Mark
 * @version 1.0
 * @className DateHelper
 * @description TODO
 * @date 2019/1/7
 **/
public class DateHelper  {

    private static int oneHour = 60;
    private static int scale = 2;

    /**
     * Determine whether two dates are the same
     * @author Mark
     * @date  2018/12/29
     * @param dateTime
     * @param dateTime2
     * @return boolean
     **/
    public static boolean isSameDate(LocalDateTime dateTime, LocalDateTime dateTime2 ) {
        LocalDate localDate1 = dateTime.toLocalDate();
        LocalDate localDate2 = dateTime2.toLocalDate();
        return localDate1.isEqual(localDate2);
    }

    /**
     * Used to calculate the number of hours spent on a given day off work
     * @author Mark
     * @date  2019/1/7
     * @param startDateTime
     * @param endWorkDateTime
     * @return double
     **/

    public static double getHoursByDates( LocalDateTime startDateTime , LocalDateTime endWorkDateTime ) {
        Duration duration = Duration.between(startDateTime,endWorkDateTime);
        return new BigDecimal(duration.toMinutes()).divide(new BigDecimal(oneHour),scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * Calculates and gets a list of all the dates between the two dates
     * @author Mark
     * @date  2018/12/28
     * @param startDate
     * @param endDate
     * @return java.util.List<LocalDateTime>
     **/
    public static List<LocalDateTime> getDateTimeList(Date startDate , Date endDate  ){
        LocalDateTime startDateTime = date2LocalDateTime(startDate);
        LocalDateTime endDateTime = date2LocalDateTime(endDate);
        List<LocalDateTime> list = new ArrayList<>();
        LocalTime localStartTime = startDateTime.toLocalTime();
        LocalDate localEndDate = endDateTime.toLocalDate();
        LocalDateTime endDateTimeTemp = localStartTime.atDate(localEndDate);
        long distance = ChronoUnit.DAYS.between(startDateTime, endDateTimeTemp);
        if (distance < 1) {
            list.add(startDateTime);
            return list;
        }
        Stream.iterate(startDateTime, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            list.add(f);
        });
        if( CollectionHelper.isNotEmpty(list) ){
            list.set(list.size()-1,endDateTime);
        }
        return list;
    }

    /**
     *
     * @author Mark
     * @date  2019/1/8
     * @param date
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime date2LocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }


    /**
     *
     * @author Mark
     * @date  2019/1/8
     * @param localDateTime
     * @return java.util.Date
     **/
    public static Date localDateTime2Date(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     *  Process the time that does not contain a date,
     *  and return a date object that is the same as the input date but has a different time
     * @author Mark
     * @date  2018/12/28
     * @param workDateTime
     * @param timeStr hh:mm:ss
     * @return LocalDateTime
     **/
    public static LocalDateTime getDateTimeByTimeStr( LocalDateTime workDateTime , String timeStr ){
        LocalDate workDate = workDateTime.toLocalDate();
        LocalTime workTime = LocalTime.parse(timeStr);
        return workTime.atDate(workDate);
    }

}
