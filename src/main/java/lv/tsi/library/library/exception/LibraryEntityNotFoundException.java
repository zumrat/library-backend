package lv.tsi.library.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LibraryEntityNotFoundException extends RuntimeException {

    public LibraryEntityNotFoundException(String message) {
        super(message);
    }

    public LibraryEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static Supplier<LibraryEntityNotFoundException> withMessage(String message) {
        return () -> new LibraryEntityNotFoundException(message);
    }
}
