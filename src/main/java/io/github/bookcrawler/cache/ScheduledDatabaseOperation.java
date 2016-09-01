package io.github.bookcrawler.cache;

import io.github.bookcrawler.core.DiscountFetchingService;
import io.github.bookcrawler.repositories.BookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@EnableScheduling
public class ScheduledDatabaseOperation {

    @Autowired
    DiscountFetchingService discountFetchingService;

    @Autowired
    AuthorsCache authorsCache;

    @Autowired
    BookInfoRepository bookInfoRepository;


    @Scheduled(cron = "0 0/1 * * * ?")
    public void saveDataInDBAndCaches() {
        authorsCache.saveAuthorsFromDBInCache();
        bookInfoRepository.save(discountFetchingService.getAllBooks());
    }
}
