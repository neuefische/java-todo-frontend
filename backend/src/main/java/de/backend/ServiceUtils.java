package de.backend;

import java.util.UUID;

public class ServiceUtils {
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
