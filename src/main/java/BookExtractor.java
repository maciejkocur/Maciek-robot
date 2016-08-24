import java.io.IOException;

public interface BookExtractor {

    Iterable<BookInfo> extract() throws IOException;
}
