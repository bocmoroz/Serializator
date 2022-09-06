package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeObject implements Serializable {

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private final String name;
    private final Date creationTime;
    private Date serializationTime;
    private Date deserializationTime;

    public TimeObject(String name) {
        this.name = name;
        this.creationTime = new Date();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        serializationTime = new Date();
        out.defaultWriteObject();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        in.defaultReadObject();
        deserializationTime = new Date();

    }

    @Override
    public String toString() {
        return "TimeObject{" +
                "name=" + name +
                ", creationTime=" + formatter.format(creationTime) +
                ", serializationTime=" + formatter.format(serializationTime) +
                ", deserializationTime=" + (deserializationTime != null ? formatter.format(deserializationTime) : null) +
                '}';
    }
}
