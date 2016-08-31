package io.github.bookcrawler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static io.github.bookcrawler.utilities.DatabaseConfigurationConstants.*;


//TODO it will be used in the future, commented out, because it was slowing down our application
//during first run
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(REPOSITORIES_PACKAGE)
//@PropertySource(CLASSPATH_TO_RESOURCES)
public class DatabaseConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(getAdapter());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(PACKAGE_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(getJPaProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public HibernateJpaVendorAdapter getAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        return adapter;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(environment.getRequiredProperty(DRIVER));
        driver.setUrl(environment.getRequiredProperty(URL));
        driver.setUsername(environment.getRequiredProperty(USERNAME));
        driver.setPassword(environment.getRequiredProperty(PASSWORD));
        return driver;
    }

    @Bean
    public Properties getJPaProperties() {
        Properties properties = new Properties();
        properties.put(DIALECT, environment.getRequiredProperty(DIALECT));
        properties.put(SHOW_SQL, environment.getRequiredProperty(SHOW_SQL));
        properties.put(FORMAT_SQL, environment.getRequiredProperty(FORMAT_SQL));
        properties.put(HBM2DDL_AUTO, environment.getRequiredProperty(HBM2DDL_AUTO));
        return properties;
    }
}
