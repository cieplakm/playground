package com.mmc.playground.designpatterns.pipeline;

public class Pipeline<I, O> {

    private final Handler<I, O> currentHandler;

    Pipeline(Handler<I, O> currentHandler) {
        this.currentHandler = currentHandler;
    }

    public static <K, J> Pipeline<K, J> startWith(Handler<K, J> handler) {
        return new Pipeline<>(handler);
    }

    <K> Pipeline<I, K> thenAccept(Handler<O, K> handler) {

        return new Pipeline<>(input -> handler.handle(currentHandler.handle(input)));
    }

    O processUsing(I input) {
        return currentHandler.handle(input);
    }
}
