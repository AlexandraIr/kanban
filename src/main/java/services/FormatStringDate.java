package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatStringDate {

    public static String date() {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return localDate.plusDays(1).format(formatter);
    }
}
