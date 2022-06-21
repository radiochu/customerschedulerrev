package Utilities;

import com.sun.scenario.effect.Offset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.now;

public class DateTimeHandler {
    public static DateTimeFormatter displayTime = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm");

    static DateTimeFormatter getHours = DateTimeFormatter.ofPattern("HH");
    static DateTimeFormatter getMinutes = DateTimeFormatter.ofPattern("mm");
    static DateTimeFormatter getSeconds = DateTimeFormatter.ofPattern("ss");

    public static ZonedDateTime localToET(LocalDateTime localTime) {
        ZoneId zoneId = ZoneId.of(ZoneId.systemDefault().getId());
        ZonedDateTime zdtAtSystemDefault = localTime.atZone(zoneId);
        return zdtAtSystemDefault.withZoneSameInstant( ZoneId.of("America/New_York"));
    }

    public static LocalDateTime etToLocal(ZonedDateTime zoneDateTime){
        ZonedDateTime zdtAtET = ZonedDateTime.now(ZoneId.of("America/New_York"));

        return zdtAtET.toLocalDateTime();
    }
    public static LocalDateTime startTime(LocalDate apptDate, LocalTime apptStart) {
        return LocalDateTime.of(apptDate.getYear(), apptDate.getMonthValue(), apptDate.getDayOfMonth(), Integer.parseInt(getHours.format(apptStart)), Integer.parseInt(getMinutes.format(apptStart)), Integer.parseInt(getSeconds.format(apptStart)));
    }

    public static LocalDateTime endTime(LocalDate apptDate, LocalTime apptEnd) {
        return LocalDateTime.of(apptDate.getYear(), apptDate.getMonthValue(), apptDate.getDayOfMonth(), Integer.parseInt(getHours.format(apptEnd)), Integer.parseInt(getMinutes.format(apptEnd)), Integer.parseInt(getSeconds.format(apptEnd)));
    }

    public static ObservableList<LocalTime> setTimeList(LocalDate date) {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        LocalDateTime start = LocalDateTime.of(date, LocalTime.of(8,0));
        LocalDateTime end = LocalDateTime.of(date, LocalTime.of(17,0));
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
