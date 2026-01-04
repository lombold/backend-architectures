package org.lombold.blueprints.hexagonal.adapter.secondary.persistency;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String id) {
        super("Could not find entity with id %s".formatted(id));
    }
}
