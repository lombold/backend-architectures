package org.lombold.blueprints.cleanarchitecture.frameworks.persistency.jpa;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String id) {
        super("Could not find entity with id %s".formatted(id));
    }
}
