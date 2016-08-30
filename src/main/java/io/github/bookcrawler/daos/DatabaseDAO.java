package io.github.bookcrawler.daos;

import io.github.bookcrawler.core.DiscountFetchingService;
import io.github.bookcrawler.repositories.BookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class DatabaseDAO {

    @Autowired
    DiscountFetchingService discountFetchingService;

    @Autowired
    BookInfoRepository bookInfoRepository;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void putBookInfIntoDatabase() throws IOException {
        bookInfoRepository.save(discountFetchingService.getAllBooks());
    }
}
