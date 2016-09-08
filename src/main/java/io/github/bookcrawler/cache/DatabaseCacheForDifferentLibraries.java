package io.github.bookcrawler.cache;

import io.github.bookcrawler.entities.BookInfo;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DatabaseCacheForDifferentLibraries {
    private Map<String, List<BookInfo>> bookInfosMap = new HashMap<>();

    public void putBookInfoFromLibrary(String library, List<BookInfo> bookInfos) {
        bookInfosMap.put(library, bookInfos);
    }

    public List<BookInfo> getBookInfosFromLibrary(String library) {
        return bookInfosMap.getOrDefault(library, Collections.emptyList());
    }

    public List<BookInfo> getAllBookInfos() {
        return bookInfosMap.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
