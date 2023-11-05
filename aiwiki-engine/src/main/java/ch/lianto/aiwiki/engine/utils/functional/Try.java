package ch.lianto.aiwiki.engine.utils.functional;

public class Try<T> {
    private T data;
    private RuntimeException exception;

    private Try(T data, RuntimeException exception) {
        this.data = data;
        this.exception = exception;
    }

    public static <T> Try<T> of(T data) {
        return new Try<>(data, null);
    }

    public static <T> Try<T> withCatch(RuntimeException ex) {
        return new Try<>(null, ex);
    }

    public boolean hasNoError() {
        return exception == null;
    }

    public T data() {
        if (exception != null)
            throw exception;
        return data;
    }

    public RuntimeException exception() {
        return exception;
    }
}
