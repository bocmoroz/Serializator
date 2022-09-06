package services;

import java.util.ArrayList;
import java.util.List;

public class ListServiceImpl implements Service {

    @Override
    public Object doWork(Object... args) {

        int size = (int) args[0];
        int from = (int) args[1];
        int till = (int) args[2];

        List<Integer> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            list.add(from + (int) (Math.random() * (till - from)));
        }

        return list;
    }
}