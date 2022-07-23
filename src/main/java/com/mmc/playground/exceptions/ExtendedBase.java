package com.mmc.playground.exceptions;

class ExtendedBase extends Base {

    @Override
    void theOneWithBaseException() {
        // throws can be removed, but super.theOneWithBaseException() must be handled or removed as well
        // will be affected by child class - child's method won't 'throws' any exception
    }

    @Override
    void theOneWithDetailedException() throws DetailedException {
        super.theOneWithDetailedException();
        // throws DetailedException CANNOT be replaced by parent i.e. BaseException
        // DetailedException CANNOT BE REPLACED BY MORE GENERIC
    }

    @Override
    void theSecondWithBaseException() throws DetailedException {
        try {
            super.theSecondWithBaseException();
        } catch (BaseException e) {
            e.printStackTrace();
        }
        // throws BaseException CAN be replaced by child i.e. DetailedException,
        // but super.theSecondWithBaseException() must be caught
        // DetailedException CAN BE REPLACED BY MORE DETAILED
    }

    @Override
    void theSecondWithDetailedException() throws DetailedException {
        // DetailedException CANNOT BE REPLACED BY NOT INHERITANCED EXCEPTION LIKE OtherException
        super.theSecondWithDetailedException();
    }

    @Override
    void theThirdWithoutAnyException() {
        super.theThirdWithoutAnyException();
        // overridden without trowing any exception CANNOT throw any exception
    }
}
