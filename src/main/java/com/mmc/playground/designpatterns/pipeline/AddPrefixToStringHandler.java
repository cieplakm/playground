package com.mmc.playground.designpatterns.pipeline;

class AddPrefixToStringHandler implements Handler<String, String> {

    public static final String PREFIX = "Prefix_";

    @Override
    public String handle(String input) {
        return PREFIX + input;
    }
}
