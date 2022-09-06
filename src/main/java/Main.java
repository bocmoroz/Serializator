import proxy.CacheProxy;
import services.ListServiceImpl;
import services.Service;
import services.TimeServiceImpl;

public class Main {

    public static void main(String[] args) {

        //подставить свой folder
        CacheProxy cacheProxy = new CacheProxy("Result");

        System.out.println("****TASK1****");
        System.out.println();

        Service listService = cacheProxy.cache(new ListServiceImpl());
        doTest1(listService);

        System.out.println();

        System.out.println("****TASK2****");
        System.out.println();

        Service timeService = cacheProxy.cache(new TimeServiceImpl());
        doTest2(timeService);

        System.out.println();
        System.out.println("****END****");
    }

    private static void doTest1(Service listService) {
        listService.doWork(10, 4, 30);
        listService.doWork(7, -7, 70);
        listService.doWork(3, 20, 30);
        listService.doWork(10, 4, 30);
    }

    private static void doTest2(Service timeService) {
        timeService.doWork("Work1");
        timeService.doWork("Work2");
        timeService.doWork("Work3");
        timeService.doWork("Work1");
    }
}