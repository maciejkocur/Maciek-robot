package io.github.bookcrawler.cache;

import io.github.bookcrawler.core.DiscountFetchingService;
import io.github.bookcrawler.repositories.BookInfoRepository;
import io.github.bookcrawler.utilities.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.EnumSet;


@Component
@EnableScheduling
public class ScheduledDatabaseOperation {

    @Autowired
    DiscountFetchingService discountFetchingService;

    @Autowired
    DatabaseCacheForDifferentLibraries databaseCacheForDifferentLibraries;
    @Autowired
    AuthorsCache authorsCache;

    @Autowired
    BookInfoRepository bookInfoRepository;

    @Scheduled(cron = "0 0 12 * * ?")
    private void schedulerJob() {
        authorsCache.saveAuthorsFromDBInCache();
        EnumSet.allOf(Library.class).stream().
                forEach(libraries -> databaseCacheForDifferentLibraries.putBookInfoFromLibrary(libraries.toString(), discountFetchingService.getBooksFromLibrary(libraries)));
        bookInfoRepository.save(databaseCacheForDifferentLibraries.getAllBookInfos());
    }
}
