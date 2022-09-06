package services;

import annotations.Cacheable;

public interface Service {

    @Cacheable(filePrefix = "WithCache_", zip = true)
    Object doWork(Object... args);
}
