package ru.geekbrains.april.market.error_handing;

import java.util.List;

public class InvalidDataException extends RuntimeException {
    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public InvalidDataException(List<String> messages) {
        this.messages = messages;
    }
}
