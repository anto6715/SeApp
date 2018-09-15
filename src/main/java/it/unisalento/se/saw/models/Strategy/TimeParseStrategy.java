package it.unisalento.se.saw.models.Strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeParseStrategy implements DateStrategy {
    @Override
    public Date parse(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date dateParsed = null;
        try {
            dateParsed = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateParsed;
    }
}
