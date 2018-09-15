package it.unisalento.se.saw.models.Strategy;

import java.util.Date;

public class Context {

    private DateStrategy dateStrategy;

    public Context(DateStrategy dateStrategy) {
        this.dateStrategy = dateStrategy;
    }

    public Date executeDateStrategy(String date) {
        return dateStrategy.parse(date);
    }
    public void changeStrategy(DateStrategy dateStrategy) {
        this.dateStrategy = dateStrategy;
    }
}
