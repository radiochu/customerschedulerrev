package Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {

    static DateTimeFormatter getHours = DateTimeFormatter.ofPattern("HH");
    static DateTimeFormatter getMinutes = DateTimeFormatter.ofPattern("mm");
    static DateTimeFormatter getSeconds = DateTimeFormatter.ofPattern("ss");

    public static LocalDateTime startTime(LocalDate apptDate, LocalTime apptStart) {
        return LocalDateTime.of(apptDate.getYear(), apptDate.getMonthValue(), apptDate.getDayOfMonth(), Integer.parseInt(getHours.format(apptStart)), Integer.parseInt(getMinutes.format(apptStart)), Integer.parseInt(getSeconds.format(apptStart)));
    }

    public static LocalDateTime endTime(LocalDate apptDate, LocalTime apptEnd) {
        return LocalDateTime.of(apptDate.getYear(), apptDate.getMonthValue(), apptDate.getDayOfMonth(), Integer.parseInt(getHours.format(apptEnd)), Integer.parseInt(getMinutes.format(apptEnd)), Integer.parseInt(getSeconds.format(apptEnd)));
    }

    public static ObservableList<LocalTime> setTimeList() {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(17, 0);
        while (start.isBefore(end.plusSeconds(1))) {
            timeList.add(start);
            start = start.plusMinutes(10);
        }
        return timeList;
    }
}
