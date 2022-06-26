package Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * The type Date time handler.
 */
public interface DateTimeHandler {
    /**
     * The Get hours.
     */
    static final DateTimeFormatter getHours = DateTimeFormatter.ofPattern("HH");
    /**
     * The Get minutes.
     */
    static final DateTimeFormatter getMinutes = DateTimeFormatter.ofPattern("mm");
    /**
     * The Get seconds.
     */
    static final DateTimeFormatter getSeconds = DateTimeFormatter.ofPattern("ss");

    /**
     * Local to et zoned date time.
     *
     * @param localTime the local time
     * @return the zoned date time
     */
    public static ZonedDateTime localToET(LocalDateTime localTime) {
        ZoneId zoneId = ZoneId.of(ZoneId.systemDefault().getId());
        ZonedDateTime zdtAtSystemDefault = localTime.atZone(zoneId);
        return zdtAtSystemDefault.withZoneSameInstant(ZoneId.of("America/New_York"));
    }

    /**
     * Et to local local date time.
     *
     * @param zoneDateTime the zone date time
     * @return the local date time
     */
    public static LocalDateTime etToLocal(ZonedDateTime zoneDateTime) {
        ZonedDateTime zdtAtET = ZonedDateTime.now(ZoneId.of("America/New_York"));

        return zdtAtET.toLocalDateTime();
    }
    /**
     * Start time local date time.
     *
     * @param apptDate  the appt date
     * @param apptStart the appt start
     * @return the local date time
     */
    public static LocalDateTime startTime(LocalDate apptDate, LocalTime apptStart) {
        return LocalDateTime.of(apptDate.getYear(), apptDate.getMonthValue(), apptDate.getDayOfMonth(), Integer.parseInt(getHours.format(apptStart)), Integer.parseInt(getMinutes.format(apptStart)), Integer.parseInt(getSeconds.format(apptStart)));
    }

    /**
     * End time local date time.
     *
     * @param apptDate the appt date
     * @param apptEnd  the appt end
     * @return the local date time
     */
    public static LocalDateTime endTime(LocalDate apptDate, LocalTime apptEnd) {
        return LocalDateTime.of(apptDate.getYear(), apptDate.getMonthValue(), apptDate.getDayOfMonth(), Integer.parseInt(getHours.format(apptEnd)), Integer.parseInt(getMinutes.format(apptEnd)), Integer.parseInt(getSeconds.format(apptEnd)));
    }

    /**
     * Sets time list.
     *
     * @param date the date
     * @return the time list
     */
    public static ObservableList<LocalTime> setTimeList(LocalDate date) {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        LocalDateTime start = LocalDateTime.of(date, LocalTime.of(8, 0));
        LocalDateTime end = LocalDateTime.of(date, LocalTime.of(17, 0));
        while (start.isBefore(end.plusSeconds(1))) {
            ZonedDateTime startEST = start.atZone(ZoneId.of("America/New_York"));
            ZonedDateTime startLocal = startEST.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime startLDT = startLocal.toLocalDateTime();
            LocalTime startTime = startLDT.toLocalTime();
            timeList.add(startTime);
            start = start.plusMinutes(10);
        }

        return timeList;
    }
}
