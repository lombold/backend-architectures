package org.lombold.blueprints.cleanarchitecture.application.output;

public interface OutputPort<Input> {
    void present(Input input);
}
