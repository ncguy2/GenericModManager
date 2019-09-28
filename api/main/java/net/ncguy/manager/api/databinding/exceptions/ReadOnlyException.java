package net.ncguy.manager.api.databinding.exceptions;

public class ReadOnlyException extends Error {

    public static void raise() {
        throw new ReadOnlyException();
    }

}
