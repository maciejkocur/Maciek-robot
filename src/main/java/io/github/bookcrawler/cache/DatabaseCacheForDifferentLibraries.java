package io.github.bookcrawler.cache;

import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.utilities.Library;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DatabaseCacheForDifferentLibraries {
    private Map<String,List<BookInfo>> bookInfosMap = new HashMap<>();

    public void putBookInfoFromLibrary(String library,List<BookInfo> bookInfos){
                       bookInfosMap.put(library,bookInfos);
    }

    public List<BookInfo> getBookInfosFromLibrary(String library){
        return bookInfosMap.getOrDefault(library, bookInfosMap.get(Library.HELION));
    }

    public List<BookInfo> getAllBookInfos(){
        return bookInfosMap.values()
                .stream()
                .flatMap(List<BookInfo>::stream)
                .collect(Collectors.toList());
    }
}
