package io.github.bookcrawler.repositories;

import io.github.bookcrawler.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AuthorsRepository extends JpaRepository<Author, Long> {

}
