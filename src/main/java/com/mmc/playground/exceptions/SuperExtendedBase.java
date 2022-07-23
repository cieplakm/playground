package com.mmc.playground.exceptions;

class SuperExtendedBase extends ExtendedBase {

    @Override
    void theOneWithBaseException() {
        super.theOneWithBaseException();
    }

    @Override
    void theOneWithDetailedException() throws DetailedException {
        super.theOneWithDetailedException();
    }

    @Override
    void theSecondWithBaseException() throws DetailedException {
        super.theSecondWithBaseException();
    }

    @Override
    void theSecondWithDetailedException() throws DetailedException {
        super.theSecondWithDetailedException();
    }

    @Override
    void theThirdWithoutAnyException() {
        super.theThirdWithoutAnyException();
    }
}
