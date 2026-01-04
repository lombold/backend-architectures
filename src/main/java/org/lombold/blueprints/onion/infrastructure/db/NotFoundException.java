package org.lombold.blueprints.onion.infrastructure.db;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String id) {
        super("Could not find entity with id %s".formatted(id));
    }
}
