package io.github.bookcrawler.config;

import io.github.bookcrawler.core.DiscountFetchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {"io.github.bookcrawler"})
public class AppContextConfig {

}
