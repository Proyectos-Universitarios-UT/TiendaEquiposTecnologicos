package com.example.tiendaeqiopostecnologicos.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    public String getDateAndTime(){
        String formater = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formater);
        LocalDateTime isNow = LocalDateTime.now();
        return formatter.format(isNow);
    }
}
