package services;

public class TimeServiceImpl implements Service {

    @Override
    public Object doWork(Object... args) {

        return new TimeObject((String) args[0]);

    }
}
