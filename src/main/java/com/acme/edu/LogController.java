package com.acme.edu;

class LogController {
    private Saver saver;

    LogController(Saver saver) {
        this.saver = saver;
    }

    Saver getSaver() {
        return this.saver;
    }
}
