package com.mmc.playground.designpatterns.pipeline;

class MakeUpperCaseHandler implements Handler<String, String> {

    @Override
    public String handle(String input) {
        return input.toUpperCase();
    }
}
