package io.github.bookcrawler.repositories;

import io.github.bookcrawler.entities.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {

    List<BookInfo> findByInputDate(Date inputDate);

    List<BookInfo> findByInputDateBetween(Date startDate, Date endDate);

    List<BookInfo> findByLibraryAndInputDate(String library,Date inputDate);
}
