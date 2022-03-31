package com.mmc.playground.designpatterns.pipeline;

public interface Handler<I, O> {

    O handle(I input);
}
