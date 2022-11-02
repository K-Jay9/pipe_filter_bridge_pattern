public interface Pipe {
    boolean isNotEmptyOrIsNotClosed();

    boolean hasNext();

    String read();

    void write(String hi_bye_foo);

    void close();
}
