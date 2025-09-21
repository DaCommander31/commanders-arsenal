package dev.dacommander31.cmda.data;

import java.util.function.Consumer;

public class ClibRunnable<T> {

    private final Consumer<T> consumer;
    private final T input;

    public ClibRunnable(Consumer<T> consumer, T input) {
        this.consumer = consumer;
        this.input = input;
    }

    public void run() {
        consumer.accept(input);
    }

}
