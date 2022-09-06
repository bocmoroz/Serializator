package proxy;

import services.Service;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private final String folder;
    private final String forFileName;

    public CacheProxy(String folder, String forFileName) {
        this.folder = folder + "\\";
        this.forFileName = forFileName;
    }

    public CacheProxy(String forFileName) {      //добавил конструктор если не указывают директорию
        this.folder = System.getProperty("user.dir") + "\\";
        this.forFileName = forFileName;
    }

    public Service cache(Service service) {

        String fileName = service.getClass().getSimpleName() + "_" + forFileName;

        MyHandler handler = new MyHandler(service, folder, fileName);

        return (Service) Proxy.newProxyInstance(Service.class.getClassLoader(),
                new Class<?>[]{Service.class},
                handler);
    }
}
