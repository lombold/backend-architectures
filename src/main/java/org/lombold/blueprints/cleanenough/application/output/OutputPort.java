package org.lombold.blueprints.cleanenough.application.output;

public interface OutputPort<Input> {
    void present(Input input);
}
