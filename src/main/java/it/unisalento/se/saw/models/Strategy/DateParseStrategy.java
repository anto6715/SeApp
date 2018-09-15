package it.unisalento.se.saw.models.Strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParseStrategy implements DateStrategy {
    @Override
    public Date parse(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = null;
        try {
            dateObj = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateObj;
    }
}
