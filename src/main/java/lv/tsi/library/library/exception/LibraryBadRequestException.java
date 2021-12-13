package lv.tsi.library.library.exception;

import java.util.function.Supplier;

public class LibraryBadRequestException extends RuntimeException {

    public LibraryBadRequestException(String message) {
        super(message);
    }

    public LibraryBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }


    public static Supplier<LibraryBadRequestException> withMessage(String message) {
        return () -> new LibraryBadRequestException(message);
    }
}
