package com.mmc.playground.designpatterns.pipeline;

class PipelinePlay {

    public static void main(String[] args) {
        String input = "aBcDeFGHIjk";
        String processedInput = Pipeline.startWith(new MakeUpperCaseHandler())
                .thenAccept(new AddPrefixToStringHandler())
                .thenAccept(new MakeUpperCaseHandler())
                .processUsing(input);

        System.out.println(processedInput);
    }
}
